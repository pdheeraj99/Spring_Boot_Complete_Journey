package Deadlock_Problem;

// File: DeadlockProblem.java
public class DeadlockProblem {

    public static void main(String[] args) {
        // These are the two shared resources (the keys)

        // * IMP * => Every object gets its own unique, built-in "lock box" when it's created.
        final Object blueKey = new Object();
        final Object redKey = new Object();

        // Priya's thread
        Thread priyaThread = new Thread(() -> {
            // Lock the blue key first

            // * IMP * => synchronized(myObj) means "grab the one and only key from blueKey's personal lock box."
            synchronized (blueKey) {
                System.out.println("Priya: I have the blue key.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Priya: Now I need the red key...");
                // Try to lock the red key (but Anil is holding it)

                // * IMPORTANT * ->   synchronized(redKey) means "grab the one and only key from redKey's personal lock box."
                // But the key is already acquired by anilThread. So waits until the key is released
                synchronized (redKey) {
                    System.out.println("Priya: I have both keys!");
                }
            }
        });

        // Anil's thread
        Thread anilThread = new Thread(() -> {
            // Lock the red key first

            // * IMP * => synchronized(redKey) means "grab the one and only key from redKey's personal lock box."
            synchronized (redKey) {
                System.out.println("Anil: I have the red key.");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Anil: Now I need the blue key...");
                // Try to lock the blue key (but Priya is holding it)

                // * IMPORTANT * ->   synchronized(blueKey) means "grab the one and only key from blueKey's personal lock box."
                // But the key is already acquired by priyaThread. So waits until the key is released
                synchronized (blueKey) {
                    System.out.println("Anil: I have both keys!");
                }
            }
        });

        priyaThread.start();
        anilThread.start();
    }
}