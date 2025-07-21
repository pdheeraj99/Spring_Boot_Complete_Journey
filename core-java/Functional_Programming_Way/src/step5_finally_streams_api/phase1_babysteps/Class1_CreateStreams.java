package step5_finally_streams_api.phase1_babysteps;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Class1_CreateStreams {

    public static void main(String[] args) {
        // 1. Creating a stream from a List (List nunchi Stream)
        List<String> names = List.of("Alice", "Bob", "Charlie");
        Stream<String> namesStream = names.stream();
        System.out.println("Stream from List:");
        namesStream.forEach(System.out::println); // Prints each name

        System.out.println("--------------------");

        // 2. Creating a stream from an Array (Array nunchi Stream)
        String[] fruits = {"Apple", "Banana", "Cherry"};
        Stream<String> fruitStream = Arrays.stream(fruits);
        System.out.println("Stream from Array:");
        fruitStream.forEach(System.out::println); // Prints each fruit

        System.out.println("--------------------");

        // 3. Creating a stream directly (Direct ga Stream create cheyadam)
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        System.out.println("Stream from Stream.of():");
        numberStream.forEach(System.out::println); // Prints each number
    }
}