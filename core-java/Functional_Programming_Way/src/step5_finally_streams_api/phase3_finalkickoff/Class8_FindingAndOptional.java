package step5_finally_streams_api.phase3_finalkickoff;

// Common problem: What if you just want to find one specific element in a stream? 😭
// And more importantly, what happens if that element doesn't exist? 😭
// This is where Java introduces a beautiful solution to prevent the dreaded NullPointerException🤞

// SAY WELCOME TO KING: Optional<T> ❤️

import java.util.List;
import java.util.Optional;

public class Class8_FindingAndOptional {
    public static void main(String[] args) {
        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Sunil");
        System.out.println("Original List: " + names);
        System.out.println("----------------------------------------");


        // 1. findFirst(): Finds the first element that matches. Returns an Optional.
        System.out.println("1. Finding the first name starting with 'S':");

        Optional<String> foundName = names.stream()
                .filter(name -> name.startsWith("S"))
                .findFirst();

        // How to safely handle the Optional "box"
        foundName.ifPresent(name -> System.out.println("  Safe way: Found name is " + name));


        // 2. What if nothing is found?
        System.out.println("\n2. Finding the first name starting with 'Z':");

        Optional<String> notFoundName = names.stream()
                .filter(name -> name.startsWith("Z"))
                .findFirst();

        // The ifPresent lambda will NOT run if the Optional is empty.
        notFoundName.ifPresent(name -> System.out.println("  This will not be printed."));
        System.out.println("  Is the Optional box empty? " + notFoundName.isEmpty());


        // 3. Providing a default value with orElse()
        System.out.println("\n3. Providing a default value:");
        String nameResult = notFoundName.orElse("No Name Found");
        System.out.println("  Result with orElse(): " + nameResult);

        // Inside your main method in FindingAndOptional.java

        System.out.println("\n4. Using findAny() to find a name with 'esh':");
        Optional<String> anyName = names.stream()
                .filter(name -> name.contains("esh"))
                .findAny();

        anyName.ifPresent(name -> System.out.println("  findAny() found: " + name));
        // In this sequential stream, it will likely find "Mahesh", but it's not guaranteed.


        // NOTE: findAny() is similar to findFirst(), but it's more efficient for parallel streams
        // as it doesn't care about order. For sequential streams, they often behave the same.
    }
}
