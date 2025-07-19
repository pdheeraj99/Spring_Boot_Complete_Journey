package Memory_Visibility_Problem;

// File: VolatileSolution.java
public class VolatileSolution {

    // THE FIX: 'volatile' forces reads/writes to the "whiteboard" (Heap).
    volatile boolean isDone = false;

    // This represents Priya's work.
    void priyasWork() {
        System.out.println("Priya: Starting my work. Will stop when the project is done.");
        // The 'volatile' rule forces Priya to check the "whiteboard" every time.
        while (!isDone) {
            // This loop will correctly terminate.
        }
        System.out.println("Priya: I see the project is done! Packing up.");
    }

    // This represents Anil's action.
    void anilFinishesProject() {
        System.out.println("Anil: Okay, I'm finishing the project now.");
        // Anil's update is immediately visible to Priya.
        isDone = true;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileSolution project = new VolatileSolution();

        // Start Priya's thread.
        Thread priyaThread = new Thread(() -> project.priyasWork());
        priyaThread.start();

        // Give Priya's thread a moment to start.
        Thread.sleep(100);

        // Start Anil's thread.
        Thread anilThread = new Thread(() -> project.anilFinishesProject());
        anilThread.start();
    }
}