package phase1;

import java.util.List;

public class Class2_StreamPipeline {

    public static void main(String[] args) {
        List<String> names = List.of("Mahesh", "Suresh", "Ramesh", "Anil", "Sunil");

        System.out.println("Stream pipeline creating...");

        // Stream Pipeline: Source -> Intermediate Operation -> Intermediate Operation -> Terminal Operation
        names.stream() // 1. Source
                .filter(name -> { // 2. Intermediate Operation
                    System.out.println("Filtering: " + name);
                    return name.length() > 5;
                })
                .map(name -> { // 3. Intermediate Operation
                    System.out.println("Mapping: " + name);
                    return name.toUpperCase();
                })
                .forEach(name -> { // 4. Terminal Operation
                    System.out.println("Printing final result: " + name);
                });

        System.out.println("Stream pipeline execution finished.");

        // Note: Trying to reuse a stream will cause an error.
        // Stream<String> myStream = names.stream();
        // myStream.forEach(System.out::println); // This works
        // myStream.forEach(System.out::println); // This will throw IllegalStateException! Stream has already been operated upon or closed.
    }

}
