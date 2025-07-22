package step999_confusion_clarification;

import java.util.function.Predicate;

public class MainClass {
    public static void main(String[] args) {

        // Check with Lambda ( what happened 🤣🤣🤣 )
        // Not intermediate class 🤦‍♀️👈. No Nonsense

        Predicate<String> bool = str -> str.length() > 5;
        System.out.println(bool.test("Dhee"));

    }
}
