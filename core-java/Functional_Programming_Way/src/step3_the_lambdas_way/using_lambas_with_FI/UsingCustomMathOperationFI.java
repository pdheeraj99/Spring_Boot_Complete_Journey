package step3_the_lambdas_way.using_lambas_with_FI;

import step3_the_lambdas_way.custom_functional_interface.MathOperation;

public class UsingCustomMathOperationFI {
    public static void main(String[] args) {

        // The verbose, anonymous class way
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };

        System.out.println("Result from anonymous class: " + addition.operate(10, 5)); // Prints: Result: 15

        // The Newest of the New, lambdas way ( Boss of the Bosses )
        MathOperation mo = (a,b) -> a+b;
        System.out.println("Result from lambdas way: " + mo.operate(1,2));
    }
}
