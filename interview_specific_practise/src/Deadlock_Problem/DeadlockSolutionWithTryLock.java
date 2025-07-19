package Deadlock_Problem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolutionWithTryLock {

    public static void main(String[] args) {
        // Use ReentrantLock instead of plain Objects
        final Lock blueKey = new ReentrantLock();
        final Lock redKey = new ReentrantLock();

        // Priya's Thread
        Thread priyaThread = new Thread(() -> {
            String threadName = "Priya";
            try {
                // Try to get the blue key, wait up to 1 second
                if (blueKey.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(threadName + ": I have the blue key.");
                    try {
                        Thread.sleep(100);
                        // Try to get the red key, wait up to 1 second
                        if (redKey.tryLock(1, TimeUnit.SECONDS)) {
                            System.out.println(threadName + ": I have both keys!");
                            try {
                                // --- CRITICAL SECTION ---
                            } finally {
                                System.out.println(threadName + ": Releasing the red key.");
                                redKey.unlock();
                            }
                        } else {
                            System.out.println(threadName + ": Couldn't get the red key, giving up.");
                        }
                    } finally {
                        System.out.println(threadName + ": Releasing the blue key.");
                        blueKey.unlock();
                    }
                } else {
                    System.out.println(threadName + ": Couldn't get the blue key, giving up.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Anil's Thread (attempts locks in opposite order)
        Thread anilThread = new Thread(() -> {
            String threadName = "Anil";
            try {
                if (redKey.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(threadName + ": I have the red key.");
                    try {
                        Thread.sleep(100);
                        if (blueKey.tryLock(1, TimeUnit.SECONDS)) {
                            System.out.println(threadName + ": I have both keys!");
                            try {
                                // --- CRITICAL SECTION ---
                            } finally {
                                System.out.println(threadName + ": Releasing the blue key.");
                                blueKey.unlock();
                            }
                        } else {
                            System.out.println(threadName + ": Couldn't get the blue key, giving up.");
                        }
                    } finally {
                        System.out.println(threadName + ": Releasing the red key.");
                        redKey.unlock();
                    }
                } else {
                    System.out.println(threadName + ": Couldn't get the red key, giving up.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        priyaThread.start();
        anilThread.start();
    }
}