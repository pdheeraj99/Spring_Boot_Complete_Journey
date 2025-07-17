import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// NOTION: https://www.notion.so/Core-Java-230649c229fb806e8132c7ee10e3944d?source=copy_link

public class FailFastVsFailSafe {

    public static void main(String[] args) {

        // Fail Fast Iterators
        List<Integer> list = new ArrayList<>(List.of(1,2,3));
        for (Integer i : list) {
            list.add(4); // *** Throws ConcurrentModificationException
        }


        // Fail Safe Iterators
        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>(List.of(1,2,3));
        for (Integer i : list1) {
            list1.add(4); // No exception, works on a snapshot
        }
        System.out.println(list1); // [1, 2, 3, 4, 4, 4]

    }

}
