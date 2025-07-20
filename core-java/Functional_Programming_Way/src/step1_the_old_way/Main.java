package step1_the_old_way;

import step1_the_old_way.by_name.SortByNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Jay", "Sanjay", "Amit"));

        // 1. Create an object of our separate class.
        Comparator<String> sortByLength = new SortByNameComparator();

        // 2. Use that object.
        Collections.sort(names, sortByLength);

        System.out.println(names); // Output: [Jay, Amit, Sanjay]
    }
}
