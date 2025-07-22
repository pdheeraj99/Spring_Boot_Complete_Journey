package step999_confusion_clarification;

import java.util.function.Predicate;

// Lambda : str -> str.length() > 0; 👇👇👇 =>
// Creates one anonymous class like below and implement the test method
// Left side: str <=> String s
// Right side: str.length() > 0; <=> { return s.length() > 5 }

public class AnonymousPredicate implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // Just some random logic
        return s.length() > 5;
    }
}
