package step3_the_lambdas_way.using_lambas_with_FI;

import java.util.function.Predicate;

public class UsingInBuiltPredicateFI {

    public static void main(String[] args) {

        // Predicate<T>
        // Method: boolean test(T t)
        // Lambda: Takes an object, returns true or false.

        Predicate<String> nameStartsWith = (str) -> str.startsWith("Dhee");
        System.out.println(nameStartsWith.test("Dsheeraj"));
    }

}
