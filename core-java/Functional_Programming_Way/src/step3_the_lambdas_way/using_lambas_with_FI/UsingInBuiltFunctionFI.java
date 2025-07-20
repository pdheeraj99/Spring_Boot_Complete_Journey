package step3_the_lambdas_way.using_lambas_with_FI;

import java.util.function.Function;

public class UsingInBuiltFunctionFI {
    public static void main(String[] args) {

        // Function<T, R> => Take object of type 'T' and expects result of type 'R'
        // Method: R apply(T t)
        // Lambda: Takes an object of type T, returns an object of type R. (Used for transformation).

        Function<String, Integer> result = (str) -> str.length();
        System.out.println(result.apply("Dheeraj"));

    }
}
