package step5_finally_streams_api.phase4_ultimate_aggregator;

import java.util.List;
import java.util.Optional;

public class Class9_ReduceOperation {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Original List: " + numbers);
        System.out.println("----------------------------------------");


        // --- Example 1: Sum of all numbers ---
        // Identity (the empty bowl): 0
        // Accumulator (the action): (bowl, next_ingredient) -> new_bowl_content
        int sum = numbers.stream()
                .reduce(0, (partialSum, nextElement) -> partialSum + nextElement);

        System.out.println("1. Sum of numbers: " + sum);

        // The same operation using a Method Reference, which is cleaner
        int sumWithMethodReference = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("   Sum using method reference: " + sumWithMethodReference);


        // --- Example 2: Find the maximum number ---
        int max = numbers.stream()
                .reduce(Integer.MIN_VALUE, (currentMax, nextElement) ->
                        currentMax > nextElement ? currentMax : nextElement);
        System.out.println("\n2. Max value: " + max);


        // --- Example 3: Reduce without an identity value ---
        // What if we don't provide an "empty bowl"?
        // The result MUST be an Optional, because the stream might be empty!
        Optional<Integer> sumOptional = numbers.stream()
                .reduce((a, b) -> a + b);

        sumOptional.ifPresent(s -> System.out.println("\n3. Sum using Optional reduce: " + s));
    }
}