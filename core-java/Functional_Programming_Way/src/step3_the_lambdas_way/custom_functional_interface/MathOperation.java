package step3_the_lambdas_way.custom_functional_interface;

@FunctionalInterface
public interface MathOperation {
    int operate(int a, int b); // Our single abstract method
}
