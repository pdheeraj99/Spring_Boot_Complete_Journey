package step5_finally_streams_api.phase4_ultimate_aggregator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Class10_UltimateCollector {
    public static void main(String[] args) {

        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Suresh"); // Note the duplicate "Suresh"
        System.out.println("Original List: " + names);
        System.out.println("----------------------------------------");

        // 1. Collectors.toList(): Collects elements into a List.
        List<String> namesAsList = names.stream()
                .collect(Collectors.toList());
        System.out.println("1. Collected as a List: " + namesAsList + " (Duplicates are allowed)");

        // 2. Collectors.toSet(): Collects elements into a Set.
        Set<String> namesAsSet = names.stream()
                .collect(Collectors.toSet());
        System.out.println("\n2. Collected as a Set: " + namesAsSet + " (Duplicates are automatically removed)");

        // 3. Collectors.toMap(): Collects elements into a Map.
        // We provide two functions: one for the key, one for the value.
        // Let's map each name to its length.
        System.out.println("\n3. Collected as a Map:");
        Map<String, Integer> namesToLengthMap = names.stream()
                 .distinct() // Using distinct() to avoid duplicate keys first! in map no duplicate keys must be present thats why checking before hand and making unique
                .collect(Collectors.toMap(
                        name -> name,             // Function to get the key
                        name -> name.length()     // Function to get the value
                ));
        System.out.println("   Map of (Name -> Length): " + namesToLengthMap);



    }
}


