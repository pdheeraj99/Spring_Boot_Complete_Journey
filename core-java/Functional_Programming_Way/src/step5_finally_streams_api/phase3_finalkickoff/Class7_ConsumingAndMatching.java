package step5_finally_streams_api.phase3_finalkickoff;

import java.util.List;

public class Class7_ConsumingAndMatching {

    public static void main(String[] args) {

        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Sunil");
        System.out.println("Original List: " + names);
        System.out.println("----------------------------------------");

        // 1. forEach(Consumer<T>): Performs an action for each element. Returns void.
        // We've used this before, but now it's official!
        System.out.println("1. forEach() -> Printing each name prefixed with 'Mr. ':");
        names.stream()
                .forEach(name -> System.out.println("  Mr. " + name));


        // 2. count(): Counts the number of elements in the stream. Returns long.
        long numberOfNames = names.stream()
                .filter(name -> name.contains("esh")) // e.g., Mahesh, Suresh, Ramesh
                .count();
        System.out.println("\n2. count() -> Number of names containing 'esh': " + numberOfNames);


        // 3. anyMatch(Predicate<T>): Checks if at least one element matches the condition.
        // Returns boolean. It's a short-circuiting operation.
        boolean isThereAnyNameWithS = names.stream()
                .anyMatch(name -> name.startsWith("S"));
        System.out.println("\n3. anyMatch() -> Is there any name starting with 'S'? " + isThereAnyNameWithS);


        // 4. allMatch(Predicate<T>): Checks if all elements match the condition.
        // Returns boolean. Also short-circuiting.
        boolean doAllNamesHaveLengthGreaterThan3 = names.stream()
                .allMatch(name -> name.length() > 3);
        System.out.println("\n4. allMatch() -> Do all names have length > 3? " + doAllNamesHaveLengthGreaterThan3);


        // 5. noneMatch(Predicate<T>): Checks if no elements match the condition.
        // Returns boolean. Also short-circuiting.
        boolean isThereNoNameToJohn = names.stream()
                .noneMatch(name -> name.equals("John"));
        System.out.println("\n5. noneMatch() -> Is there no name equal to 'John'? " + isThereNoNameToJohn);
    }
}