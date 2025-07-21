package step5_finally_streams_api.phase2_preparingstream;

import java.util.List;
import java.util.stream.Collectors;

// A simple record to represent a User. Records are great for immutable data carriers.
record User(String name) {
    // The constructor is automatically created by the record.
}

public class Class4_TransformingData {
    public static void main(String[] args) {
        // Example 1: Transforming a list of Strings to a list of their lengths (Integers)
        System.out.println("--- Example 1: Transforming String to Integer ---");
        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Sunil");
        System.out.println("Original Names (List<String>): " + names);

        // Using map to get the length of each name
        List<Integer> nameLengths = names.stream()
                .map(name -> name.length()) // The function: takes a String, returns an Integer
                .collect(Collectors.toList()); // Terminal operation to collect results into a new list

        System.out.println("Transformed Lengths (List<Integer>): " + nameLengths);

        System.out.println("\n------------------------------------------------\n");


        // Example 2: Transforming a list of Strings into a list of custom objects (Users)
        System.out.println("--- Example 2: Transforming String to User Object ---");
        List<String> userNames = List.of("Alice", "Bob", "Charlie");
        System.out.println("Original User Names (List<String>): " + userNames);

        // Using map to create a User object for each name
        List<User> users = userNames.stream()
                .map(name -> new User(name)) // The function: takes a String, returns a User object
                .collect(Collectors.toList());

        System.out.println("Transformed User Objects (List<User>):");
        users.forEach(user -> System.out.println("  " + user));
    }
}
