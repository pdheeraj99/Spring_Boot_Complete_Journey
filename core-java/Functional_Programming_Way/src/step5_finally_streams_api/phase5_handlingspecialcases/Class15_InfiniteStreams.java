package step5_finally_streams_api.phase5_handlingspecialcases;

import java.util.UUID;
import java.util.stream.Stream;

public class Class15_InfiniteStreams {
    public static void main(String[] args) {
        // --- 1. Stream.iterate(): Creates a sequential, ordered stream ---
        // It starts with a "seed" and applies a function to the previous element
        // to generate the next one. (0 -> 0+2=2 -> 2+2=4 -> 4+2=6 ...)
        System.out.println("--- 1. First 10 even numbers using iterate() ---");
        Stream.iterate(0, n -> n + 2) // seed = 0, function = n + 2
                .limit(10) // <-- CRITICAL! This makes the infinite stream finite.
                .forEach(n -> System.out.print(n + " "));

        // --- 2. Stream.generate(): Creates a stream of unordered elements ---
        // It takes a "Supplier" function and just calls it over and over.
        // Each element is generated independently.
        System.out.println("\n\n--- 2. Five random numbers using generate() ---");
        Stream.generate(Math::random) // The supplier is the Math.random() method
                .limit(5) // <-- CRITICAL!
                .forEach(System.out::println);

        System.out.println("\n--- 3. Five random UUIDs using generate() ---");
        Stream.generate(UUID::randomUUID)
                .limit(5)
                .forEach(System.out::println);
    }
}
