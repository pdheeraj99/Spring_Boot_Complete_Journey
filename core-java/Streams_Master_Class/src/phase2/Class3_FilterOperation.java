package phase2;

import java.util.List;

public class Class3_FilterOperation {
    public static void main(String[] args) {
        // Example 1: Filtering Even Numbers from a list of Integers
        System.out.println("--- Example 1: Filtering Even Numbers ---");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original Numbers: " + numbers);

        System.out.print("Filtered (Even) Numbers: ");
        numbers.stream()
                .filter(n -> n % 2 == 0) // The predicate: returns true only for even numbers
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n----------------------------------------\n");


        // Example 2: Filtering names that start with 'S'
        System.out.println("--- Example 2: Filtering Names ---");
        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Sunil");
        System.out.println("Original Names: " + names);

        System.out.println("Filtered Names (starting with 'S'):");
        names.stream()
                .filter(name -> name.startsWith("S")) // The predicate: returns true for names starting with 'S'
                .forEach(System.out::println);
    }
}

