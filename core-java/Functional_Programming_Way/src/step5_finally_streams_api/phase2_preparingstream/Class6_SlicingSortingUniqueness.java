package step5_finally_streams_api.phase2_preparingstream;

import java.util.List;
import java.util.stream.Collectors;

public class Class6_SlicingSortingUniqueness {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(10, 4, 8, 4, 1, 15, 8, 10, 3);
        System.out.println("Original Messy List: " + numbers);
        System.out.println("----------------------------------------");


        // 1. distinct(): Removes duplicates
        List<Integer> distinctNumbers = numbers.stream()
                .distinct() // Removes the duplicate 4, 8, and 10
                .collect(Collectors.toList());
        System.out.println("1. distinct() -> " + distinctNumbers);


        // 2. sorted(): Sorts the elements (natural order)
        List<Integer> sortedNumbers = numbers.stream()
                .sorted() // Sorts the numbers from smallest to largest
                .collect(Collectors.toList());
        System.out.println("2. sorted() -> " + sortedNumbers);


        // 3. limit(n): Takes only the first 'n' elements
        List<Integer> limitedNumbers = numbers.stream()
                .limit(5) // Takes only the first 5 elements from the original stream
                .collect(Collectors.toList());
        System.out.println("3. limit(5) -> " + limitedNumbers);


        // 4. skip(n): Skips the first 'n' elements
        List<Integer> skippedNumbers = numbers.stream()
                .skip(5) // Skips the first 5 and takes the rest
                .collect(Collectors.toList());
        System.out.println("4. skip(5) -> " + skippedNumbers);


        System.out.println("\n--- The Power of Chaining! ---");
        // Let's find the 3 smallest UNIQUE numbers
        List<Integer> result = numbers.stream()
                .distinct()      // Step 1: Remove duplicates -> [10, 4, 8, 1, 15, 3]
                .sorted()        // Step 2: Sort them -> [1, 3, 4, 8, 10, 15]
                .limit(3)        // Step 3: Take the first 3 -> [1, 3, 4]
                .collect(Collectors.toList());

        System.out.println("The 3 smallest unique numbers are: " + result);
    }
}
