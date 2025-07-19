package Atomicity_Problem;

// File: SynchronizedSolution.java
public class SynchronizedSolution {

    private int count = 0;

    // THE FIX: The 'synchronized' keyword acts as a lock.
    // This makes the entire method atomic.
    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedSolution eventCounter = new SynchronizedSolution();
        int incrementsPerThread = 500_000;

        Runnable task = () -> {
            for (int i = 0; i < incrementsPerThread; i++) {
                eventCounter.increment();
            }
        };

        Thread priyaThread = new Thread(task);
        Thread anilThread = new Thread(task);

        priyaThread.start();
        anilThread.start();

        priyaThread.join();
        anilThread.join();

        int expectedCount = incrementsPerThread * 2;
        System.out.println("Expected final count: " + expectedCount);
        System.out.println("Actual final count:   " + eventCounter.count);
    }
}