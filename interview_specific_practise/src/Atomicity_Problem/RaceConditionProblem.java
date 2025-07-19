package Atomicity_Problem;

public class RaceConditionProblem {

    // The shared tally counter starts at 0.
    private int count = 0;

    // This is the non-atomic operation that causes the race condition.
    public void increment() {
        count++; // Internally: Read, Modify, Write
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionProblem eventCounter = new RaceConditionProblem();
        int incrementsPerThread = 500_000;

        // This is the task for both Priya's and Anil's threads.
        Runnable task = () -> {
            for (int i = 0; i < incrementsPerThread; i++) {
                eventCounter.increment();
            }
        };

        Thread priyaThread = new Thread(task);
        Thread anilThread = new Thread(task);

        // Start both threads to run concurrently.
        priyaThread.start();
        anilThread.start();

        // Wait for both threads to finish their work.
        priyaThread.join();
        anilThread.join();

        int expectedCount = incrementsPerThread * 2;
        System.out.println("Expected final count: " + expectedCount);
        System.out.println("Actual final count:   " + eventCounter.count);
    }
}