package step5_finally_streams_api.phase4_ultimate_aggregator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Adding a salary field to our Employee record
record Employee1(String name, String department, int salary) {}

public class Class12_AdvancedCollectors {
    public static void main(String[] args) {
        List<Employee1> employees = List.of(
                new Employee1("Alice", "HR", 60000),
                new Employee1("Bob", "IT", 90000),
                new Employee1("Charlie", "IT", 110000),
                new Employee1("David", "Sales", 85000),
                new Employee1("Eve", "HR", 75000)
        );

        // --- 1. partitioningBy(Predicate): A special case of groupingBy ---
        // It splits the stream into exactly two groups: true and false.
        System.out.println("--- 1. Partitioning employees by high salary (>= 90000) ---");

        // partitioningBy is a recipe that sorts items into two lists, true and false
        Map<Boolean, List<Employee1>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.salary() >= 90000));
        System.out.println("High Earners (true): " + partitionedBySalary.get(true));
        System.out.println("Others (false): " + partitionedBySalary.get(false));

        // --- 2. joining(): For streams of strings ---
        System.out.println("\n--- 2. Joining all employee names into a single string ---");
        String allNames = employees.stream()
                .map(Employee1::name) // First, we need a Stream<String>. It is returning all names from Employee1 object
                .collect(Collectors.joining(", "));
        System.out.println(allNames);

        // --- 3. Downstream Collectors: The real power-up! ---
        // groupingBy can take a SECOND collector to process the values in each group.

        // a) Group by department AND count the employees in each.
        System.out.println("\n--- 3a. Grouping by department and COUNTING employees ---");
        Map<String, List<Employee1>> groupByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::department       // Downstream collector (what to do with the values)
                ));
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::department,
                        Collectors.counting() // Downstream collector (what to do with the values)
                ));
        System.out.println("Grouping by Dept:::");
        System.out.println(groupByDept);

        System.out.println("Counting by Dept:::");
        System.out.println(countByDept);

        // b) Group by department AND sum the salaries in each.
        System.out.println("\n--- 3b. Grouping by department and SUMMING salaries ---");
        Map<String, Integer> sumOfSalariesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee1::department,
                        Collectors.summingInt(Employee1::salary) // Downstream collector
                ));
        System.out.println(sumOfSalariesByDept);


    }
}
