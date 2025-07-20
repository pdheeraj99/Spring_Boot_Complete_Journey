package step3_the_lambdas_way.using_lambas_with_FI;

import java.util.function.Supplier;

public class UsingInBuiltSupplierFI {
    public static void main(String[] args) {

        // Supplier<T>
        // Method: T get()
        // Lambda: Takes nothing, returns an object. (Used for generating things).

        Supplier<String> s = () -> {
            return "Hello Dheeraj";
        };
        System.out.println(s.get());

    }
}
