package EfficientThreadCommunication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerSolutionUsingQueue {

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5; // The conveyor belt can hold 5 cookies
    private final Random random = new Random();

    // Anil's job: Produce cookies
    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) { // Lock the shared resource (the bakery room)
                // --- The Producer's Wait Condition ---
                // If the buffer is full, Anil must wait.
                while (buffer.size() == capacity) {
                    System.out.println("Anil: Belt is full. Time for a coffee break... (waiting)");
                    wait(); // Releases the lock and goes to sleep
                }

                // Add a new "cookie" (an integer) to the buffer
                int cookie = random.nextInt(100);
                buffer.add(cookie);
                System.out.println("Anil: Baked a cookie! (" + cookie + ") | Belt size: " + buffer.size());

                // --- Notify ---
                // Wake up any waiting threads (Priya) because something has changed.
                notifyAll();
            }
            Thread.sleep(random.nextInt(1000)); // Simulate time taken to bake
        }
    }

    // Priya's job: Consume cookies
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) { // Lock the shared resource
                // --- The Consumer's Wait Condition ---
                // If the buffer is empty, Priya must wait.
                while (buffer.isEmpty()) {
                    System.out.println("Priya: Belt is empty. Waiting for cookies... (waiting)");
                    wait(); // Releases the lock and goes to sleep
                }

                // Remove a "cookie" from the buffer
                int cookie = buffer.poll();
                System.out.println("Priya: Packed a cookie! (" + cookie + ")  | Belt size: " + buffer.size());

                // --- Notify ---
                // Wake up any waiting threads (Anil) because space is now available.
                notifyAll();
            }
            Thread.sleep(random.nextInt(1000)); // Simulate time taken to pack
        }
    }

    public static void main(String[] args) {
        ProducerConsumerSolutionUsingQueue bakery = new ProducerConsumerSolutionUsingQueue();

        // Anil the Producer
        Thread anilThread = new Thread(() -> {
            try {
                bakery.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Priya the Consumer
        Thread priyaThread = new Thread(() -> {
            try {
                bakery.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        anilThread.start();
        priyaThread.start();
    }
}