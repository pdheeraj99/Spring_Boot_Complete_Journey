package step5_finally_streams_api.phase7_endingstreams;

import java.util.List;
import java.util.stream.Collectors;

public class DebuggingStreams {
    public static void main(String[] args) {

        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Sunil");

        System.out.println("--- Goal: Get the length of names starting with 'S', in uppercase ---");

        // Let's build the pipeline step-by-step and use peek() to see the flow.
        List<Integer> lengths = names.stream()
                .peek(name -> System.out.println("1. Original element: " + name)) // Window 1: See the original stream

                .filter(name -> name.startsWith("S"))
                .peek(name -> System.out.println("2. After filter:     " + name)) // Window 2: See what comes out of filter

                .map(String::toUpperCase)
                .peek(name -> System.out.println("3. After map:        " + name)) // Window 3: See what comes out of map

                .map(String::length)
                .peek(length -> System.out.println("4. After length map: " + length)) // Window 4: See the final integer values

                .collect(Collectors.toList());

        System.out.println("\nFinal Result: " + lengths);
    }
}
