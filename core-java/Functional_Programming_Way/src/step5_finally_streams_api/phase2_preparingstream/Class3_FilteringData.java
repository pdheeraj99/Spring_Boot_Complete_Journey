package step5_finally_streams_api.phase2_preparingstream;

// Filtering Data

import java.util.List;

// filter anedi elements ni select cheyadaniki or reject cheyadaniki
public class Class3_FilteringData {
    public static void main(String[] args) {
        // Example 1: Filtering Even Numbers from a list of Integers
        System.out.println("--- Example 1: Filtering Even Numbers ---");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Original Numbers without modifying "+numbers);

        System.out.println("Filtering even numbers:::");
        numbers.stream().filter((number) -> number % 2 == 0).forEach(System.out::println);

    }
}
