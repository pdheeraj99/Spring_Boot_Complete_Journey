package step1_the_old_way.by_name;

import java.util.Comparator;

// A complete class in its own file, just to hold one method.
public class SortByNameComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        // The one piece of logic we actually care about.
        return s1.length() - s2.length();
    }
}
