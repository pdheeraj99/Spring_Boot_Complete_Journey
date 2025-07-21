package step3_the_lambdas_way.using_lambas_with_FI;

import java.util.function.Predicate;

// Normal Scenario:
// => Think of this / current file class as Class1 ( UsingInBuiltPredicateFI )
// => for Predicate interface which we assume that we created class for it and also implemented it
//      and lets call it as Class2 ( implementing Predicate )
// => Now to use one method ( named test ) in CLass2 from CLass1 what we do ? - > We will simply create Class2
//      obj and assign its reference to variable in Class1 and call the method right using the reference
// => So here we will create "  Predicate<String> nameStartsWith " Predicate reference
// => " (str) -> str.startsWith("Dhee"); " : This takes care of class implementing Predicate and
//       implementing the method
// Simple 🙏🙏🙏

public class UsingInBuiltPredicateFI {

    public static void main(String[] args) {

        // Predicate<T>
        // Method: boolean test(T t)
        // Lambda: Takes an object, returns true or false.

        Predicate<String> nameStartsWith = (str) -> str.startsWith("Dhee");
        System.out.println(nameStartsWith.test("Dsheeraj"));
    }

}

