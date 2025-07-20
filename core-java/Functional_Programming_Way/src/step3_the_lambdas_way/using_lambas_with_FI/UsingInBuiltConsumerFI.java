package step3_the_lambdas_way.using_lambas_with_FI;

import java.util.function.Consumer;

public class UsingInBuiltConsumerFI {
    public static void main(String[] args) {

        // Consumer<T>
        //  Method: void accept(T t)
        //  Lambda: Takes an object, returns nothing. (Used for actions).

        Consumer<String> printsSomething = (str) -> System.out.println(str);
        printsSomething.accept("Dheeraj");
    }
}
