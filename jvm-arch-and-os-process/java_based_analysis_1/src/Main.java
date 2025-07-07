public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello Dheeraj 1");

        Thread myThread = new Thread(() -> {
            while (true) {
                System.out.println("Running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "MyThread1");

        System.out.println("Thread created: " + myThread.getName() + myThread.getContextClassLoader()); // Confirm creation
        myThread.start();

    }
}