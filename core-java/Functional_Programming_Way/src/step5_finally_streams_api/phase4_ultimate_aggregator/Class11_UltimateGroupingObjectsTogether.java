package step5_finally_streams_api.phase4_ultimate_aggregator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// A simple record to represent an Employee
record Employee(String name, String department) {}

public class Class11_UltimateGroupingObjectsTogether {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Alice", "HR"),
                new Employee("Bob", "IT"),
                new Employee("Charlie", "IT"),
                new Employee("David", "Sales"),
                new Employee("Eve", "HR")
        );

        System.out.println("--- Grouping Employees by Department ---");

        // The classifier function is Employee::department
        // This tells groupingBy to use the department string as the key.
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));

        // Let's print the resulting map
        employeesByDept.forEach((department, empList) -> {
            System.out.println("Department: " + department);
            empList.forEach(emp -> System.out.println("  - " + emp.name()));
        });

    }

}
