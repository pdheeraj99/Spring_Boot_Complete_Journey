package Deadlock_Problem;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSolutionWithReEntrantTryLock {

    public static void main(String[] args) {
        final Lock blueKey = new ReentrantLock();
        final Lock redKey = new ReentrantLock();
        final Random random = new Random();

        // Priya's Thread with a retry loop
        Thread priyaThread = new Thread(() -> {
            String threadName = "Priya";
            while (true) { // Keep trying until work is done
                try {
                    if (blueKey.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println(threadName + ": Got the blue key.");
                        try {
                            if (redKey.tryLock(1, TimeUnit.SECONDS)) {
                                System.out.println(threadName + ": Got both keys! Doing my work.");
                                // --- CRITICAL SECTION ---
                                break; // Success! Exit the while loop.
                            } else {
                                System.out.println(threadName + ": Couldn't get red key. Backing off.");
                            }
                        } finally {
                            blueKey.unlock(); // Always release the first lock if second fails
                        }
                    } else {
                        System.out.println(threadName + ": Couldn't get blue key. Trying again later.");
                    }
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                // Wait for a random moment before retrying to avoid constant clashes
                try { Thread.sleep(random.nextInt(100)); } catch (InterruptedException e) {}
            }
        });

        // Anil's Thread with a retry loop
        Thread anilThread = new Thread(() -> {
            String threadName = "Anil";
            while (true) { // Keep trying until work is done
                try {
                    if (redKey.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println(threadName + ": Got the red key.");
                        try {
                            if (blueKey.tryLock(1, TimeUnit.SECONDS)) {
                                System.out.println(threadName + ": Got both keys! Doing my work.");
                                // --- CRITICAL SECTION ---
                                break; // Success! Exit the while loop.
                            } else {
                                System.out.println(threadName + ": Couldn't get blue key. Backing off.");
                            }
                        } finally {
                            redKey.unlock();
                        }
                    } else {
                        System.out.println(threadName + ": Couldn't get red key. Trying again later.");
                    }
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

                try { Thread.sleep(random.nextInt(100)); } catch (InterruptedException e) {}
            }
        });

        priyaThread.start();
        anilThread.start();
    }
}