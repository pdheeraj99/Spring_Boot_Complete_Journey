package step5_finally_streams_api.phase6_modernjavastreamfeatures;

import java.util.List;
import java.util.stream.Stream;

public class Class16_ModernStreamMethods {
    public static void main(String[] args) {
        Stream<String> namesStream = Stream.of("Alice", "Bob", "Charlie");

        // --- 1. The Old Way vs. The New Way: .toList() ---
        // Before Java 16, you had to use a Collector.
        // List<String> oldWay = namesStream.collect(Collectors.toList());

        // In Java 16+, you can call .toList() directly. It's cleaner!
        // Note: This returns an UNMODIFIABLE list.
        System.out.println("--- 1. Using Stream.toList() ---");
        List<String> modernList = namesStream.toList();
        System.out.println("Collected List: " + modernList);

        // Trying to modify this list will throw an exception!
        try {
            modernList.add("David");
        } catch (UnsupportedOperationException e) {
            System.out.println("  As expected, the list is unmodifiable: " + e.getMessage());
        }


        // --- 2. flatMap vs. mapMulti() ---
       // System.out.println("\n--- 2. Using mapMulti for filtering and mapping ---");
        // A more complex example. Let's process a stream of strings.
        // If a string is a number, convert it to an integer and then repeat it that many times.
        //Stream<String> mixedStream = Stream.of("apple", "2", "cherry", "3");

//        List<Integer> result = mixedStream
//                .mapMulti((str, downstream) -> {
//                    try {
//                        int num = Integer.parseInt(str); // Try to convert to a number
//                        for (int i = 0; i < num; i++) {
//                            downstream.accept(num); // Add the number 'num' times
//                        }
//                    } catch (NumberFormatException e) {
//                        // If it's not a number, do nothing (effectively filtering it out)
//                    }
//                });

      //  System.out.println("Result from mapMulti: " + result); // Should be [2, 2, 3, 3, 3]

    }
}
