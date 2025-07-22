package step5_finally_streams_api.phase5_handlingspecialcases;

import java.util.stream.LongStream;

public class Class14_ParallelStreams {
    public static void main(String[] args) {

        long numberOfElements = 100011L;

        // --- 1. Sequential Stream Processing ( Used for small list ) ---
        System.out.println("--- 1. Sequential Sum ---");
        long startTimeSequential = System.currentTimeMillis();

        long sequentialSum = LongStream.rangeClosed(1, numberOfElements)
                .sum();

        long endTimeSequential = System.currentTimeMillis();
        System.out.println("Sequential Sum: " + sequentialSum);
        System.out.println("Time taken: " + (endTimeSequential - startTimeSequential) + " ms\n");


        // --- 2. Parallel Stream Processing ( Used for very heavy / large list ) ---
        // To make a stream parallel, you just add the .parallel() method!
        System.out.println("--- 2. Parallel Sum ---");
        long startTimeParallel = System.currentTimeMillis();

        long parallelSum = LongStream.rangeClosed(1, numberOfElements)
                .parallel() // This is the only change needed!
                .sum();

        long endTimeParallel = System.currentTimeMillis();
        System.out.println("Parallel Sum: " + parallelSum);
        System.out.println("Time taken: " + (endTimeParallel - startTimeParallel) + " ms");

    }
}
