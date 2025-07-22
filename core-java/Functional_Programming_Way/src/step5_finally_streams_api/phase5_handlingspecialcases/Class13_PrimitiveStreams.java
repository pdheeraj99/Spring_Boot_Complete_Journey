package step5_finally_streams_api.phase5_handlingspecialcases;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class Class13_PrimitiveStreams {
    public static void main(String[] args) {

        // --- 1. Creating an IntStream ---
        System.out.println("--- 1. Creating an IntStream 👇 ---");
        // Instead of Stream.of(1, 2, 3), we use IntStream.of()
        IntStream.of(1, 2, 3, 4, 5)
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n\n--- 2. Using range() to create a sequence 👇 ---");
        // range(start, endExclusive) is a very common way to create an IntStream
        IntStream.range(1, 10) // Creates a stream from 1 to 9
                .forEach(n -> System.out.print(n + " "));

        // --- 3. Specialized Methods: sum(), average(), etc. ---
        System.out.println("\n\n--- 3. Specialized Methods 👇 ---");
        // Calculate the sum of numbers from 1 to 100
        int sum = IntStream.rangeClosed(1, 100) // rangeClosed is inclusive of the end value
                .sum();
        System.out.println("Sum of 1 to 100: " + sum);

        // --- 4. Summary Statistics ---
        System.out.println("\n--- 4. Summary Statistics ---");
        // This is a powerful feature of primitive streams
        IntSummaryStatistics stats = IntStream.of(7, 2, 8, 1, 9, 4)
                .summaryStatistics();

        System.out.println("Statistics: " + stats);
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Max: " + stats.getMax());
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Average: " + stats.getAverage());

    }
}
