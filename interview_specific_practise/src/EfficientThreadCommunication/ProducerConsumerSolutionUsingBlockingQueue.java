package EfficientThreadCommunication;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerSolutionUsingBlockingQueue {

    // 1. Use a BlockingQueue. ArrayBlockingQueue is a common choice with a fixed capacity.
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5); // Capacity of 5
    private final Random random = new Random();

    // Anil's job: Produce cookies
    public void produce() throws InterruptedException {
        while (true) {
            // 2. The put() method is blocking.
            // If the buffer is full, it will WAIT automatically.
            int cookie = random.nextInt(100);
            buffer.put(cookie);
            System.out.println("Anil: Baked a cookie! (" + cookie + ") | Belt size: " + buffer.size());

            Thread.sleep(random.nextInt(1000)); // Simulate time taken to bake
        }
    }

    // Priya's job: Consume cookies
    public void consume() throws InterruptedException {
        while (true) {
            // 3. The take() method is blocking.
            // If the buffer is empty, it will WAIT automatically.
            int cookie = buffer.take();
            System.out.println("Priya: Packed a cookie! (" + cookie + ")  | Belt size: " + buffer.size());

            Thread.sleep(random.nextInt(1000)); // Simulate time taken to pack
        }
    }

    public static void main(String[] args) {
        ProducerConsumerSolutionUsingBlockingQueue bakery = new ProducerConsumerSolutionUsingBlockingQueue();

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
