package Deadlock_Problem;

// File: DeadlockSolution.java
public class DeadlockSolutionWithLockOrder {

    public static void main(String[] args) {
        final Object blueKey = new Object();
        final Object redKey = new Object();

        // Priya's thread
        Thread priyaThread = new Thread(() -> {
            // Lock order: blue -> red
            // Synchronized Block
            synchronized (blueKey) {
                System.out.println("Priya: I have the blue key.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Priya: Now I need the red key...");
                synchronized (redKey) {
                    System.out.println("Priya: I have both keys!");
                }
            }
        });

        // Anil's thread
        Thread anilThread = new Thread(() -> {
            // THE FIX: Enforce the same lock order: blue -> red
            synchronized (blueKey) {
                System.out.println("Anil: I have the blue key.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Anil: Now I need the red key...");
                synchronized (redKey) {
                    System.out.println("Anil: I have both keys!");
                }
            }
        });

        priyaThread.start();
        anilThread.start();
    }
}