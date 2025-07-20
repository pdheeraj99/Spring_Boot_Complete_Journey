package step2_anonymous_classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Jay", "Sanjay", "Amit"));

        // Define the class and create the object all at once!
        Comparator<String> sortByLength = new Comparator<String>() { // <-- The magic is here
            @Override
            public int compare(String s1, String s2) {
                // The logic is now inside the main file.
                return s1.length() - s2.length();
            }
        }; // The semicolon here ends the object creation statement.

        Collections.sort(names, sortByLength);
        System.out.println(names); // Output: [Jay, Amit, Sanjay]
    }
}
