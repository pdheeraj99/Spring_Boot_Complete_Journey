package step4_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class UsingMethodRefVersion {

    public static void main(String[] args) {

        //  1. Reference to a Static Method ---------------------------------
        //  This is used when your lambda just calls a static method.

        //  Syntax: ClassName::staticMethodName

        // Lambda version
        Function<String, Integer> parser = s -> Integer.parseInt(s);

        // Method Reference version (cleaner)
        Function<String, Integer> parserRef = Integer::parseInt;

        System.out.println(parserRef.apply("123")); // Prints 123

        // ---------------------------------------------------------------

        // 2. Reference to an Instance Method of a Particular Object
        // This is used when your lambda calls a method on a specific, existing object. System.out is a great example of an existing object.

        // Syntax: instanceObject::instanceMethodName

        // Lambda version
        Consumer<String> printer = s -> System.out.println(s);

        // Method Reference version (very common)
        Consumer<String> printerRef = System.out::println;

        printerRef.accept("Hello, Method Reference!");


        // Reference to an Instance Method of an Arbitrary Object
        // This one looks similar to a static reference but is conceptually different. It's used when the lambda's first parameter is the object on which the method is called.
        //
        // Syntax: ClassName::instanceMethodName

        // Lambda version
        // The lambda takes a String 's' and calls the length() method ON 's'.
        Function<String, Integer> lengthFinder = s -> s.length();

        // Method Reference version
        // The compiler knows to take the input String and call length() on it.
        Function<String, Integer> lengthFinderRef = String::length;

        System.out.println(lengthFinderRef.apply("Java")); // Prints 4

        // 4. Reference to a Constructor
        // This is used when your lambda just creates a new object.
        //
        // Syntax: ClassName::new

        // Lambda version
        Supplier<List<String>> listFactory = () -> new ArrayList<>();

        // Method Reference version
        Supplier<List<String>> listFactoryRef = ArrayList::new;

        List<String> myList = listFactoryRef.get();
        System.out.println(myList); // Prints []
    }

}
