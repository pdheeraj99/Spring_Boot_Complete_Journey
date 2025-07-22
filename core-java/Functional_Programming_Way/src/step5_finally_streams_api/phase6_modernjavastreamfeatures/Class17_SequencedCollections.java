package step5_finally_streams_api.phase6_modernjavastreamfeatures;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Class17_SequencedCollections {
    public static void main(String[] args) {

        List<String> namesList = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));
        System.out.println("--- 1. Sequenced Methods on a List ---");
        System.out.println("Original List: " + namesList);

        // New methods to get first/last elements directly
        System.out.println("  getFirst(): " + namesList.getFirst());
        System.out.println("  getLast(): " + namesList.getLast());

        // The most important new method for streams: reversed()
        System.out.println("\n  Processing the list's stream in REVERSE order:");
        namesList.reversed() // Returns a reversed VIEW of the collection
                .stream()   // Create a stream from that reversed view
                .forEach(name -> System.out.print(name + " ")); // Prints "Charlie Bob Alice"


        System.out.println("\n\n--- 2. Sequenced Methods on a LinkedHashSet ---");
        // LinkedHashSet is a Set that remembers insertion order. It now implements SequencedSet.
        Set<String> namesSet = new LinkedHashSet<>(Set.of("David", "Eve", "Frank"));
        System.out.println("Original LinkedHashSet: " + namesSet);

//        // These methods did NOT exist on Set before Java 21!
//        System.out.println("  getFirst(): " + namesSet.getFirst());
//        System.out.println("  getLast(): " + namesSet.getLast());
//
//        System.out.println("\n  Processing the set's stream in REVERSE order:");
//        namesSet.reversed()
//                .stream()
//                .forEach(name -> System.out.print(name + " ")); // Prints "Frank Eve David"
    }
}
