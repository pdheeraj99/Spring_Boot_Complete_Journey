package step5_finally_streams_api.phase2_preparingstream;

import java.util.List;
import java.util.stream.Collectors;

public class Class5_FlatMapOperation {

    public static void main(String[] args) {

        // The "List of Lists" scenario
        List<List<Integer>> listOfLists = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6)
        );
        System.out.println("Original nested list: " + listOfLists);

        // --- The 'map' Trap ---
        // 'map' just opens the outer box, leaving you with the inner lists.
        System.out.println("\nUsing map (The WRONG way for this problem):");
        List<List<Integer>> mappedList = listOfLists.stream()
                .map(list -> list) // This doesn't really change anything
                .collect(Collectors.toList());
        System.out.println("Result with map: " + mappedList + " <-- See? Still a list of lists!");


        // --- The 'flatMap' Superpower ---
        // 'flatMap' opens ALL boxes and puts all items onto one flat table.
        System.out.println("\nUsing flatMap (The RIGHT way):");
        List<Integer> flatList = listOfLists.stream() // Stream<List<Integer>>
                .flatMap(list -> list.stream()) // Flattens it into Stream<Integer>
                .collect(Collectors.toList());
        System.out.println("Result with flatMap: " + flatList + " <-- Perfect! One single flat list.");

    }
}