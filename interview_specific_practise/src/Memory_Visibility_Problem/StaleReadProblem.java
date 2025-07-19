package Memory_Visibility_Problem;

// File: StaleReadProblem.java
public class StaleReadProblem {

    // The shared whiteboard status. Not volatile.
    boolean isDone = false;

    // This represents Priya's work.
    void priyasWork() {
        System.out.println("Priya: Starting my work. Will stop when the project is done.");
        // Priya keeps checking her own "sticky note" (local cache).
        while (!isDone) {
            // This loop might run forever.
        }
        System.out.println("Priya: I see the project is done! Packing up.");
    }

    // This represents Anil's action.
    void anilFinishesProject() {
        System.out.println("Anil: Okay, I'm finishing the project now.");
        // Anil updates the main "whiteboard" (Heap).
        isDone = true;
    }

    public static void main(String[] args) throws InterruptedException {
        StaleReadProblem project = new StaleReadProblem();

        // Start Priya's thread. She will start her `while` loop.
        Thread priyaThread = new Thread(() -> project.priyasWork());
        priyaThread.start();

        // Give Priya's thread a moment to get into its loop.
        Thread.sleep(100);

        // Start Anil's thread. He will change the status.
        Thread anilThread = new Thread(() -> project.anilFinishesProject());
        anilThread.start();
    }
}
