package step999_confusion_clarification;

import java.util.List;
import java.util.function.Predicate;

// Not only Predicate this applies to all FI's ( Eg., Consumer, Supplier, Function, Our own custom FI's )
public class IsPredicateAnObject {
    public static void main(String[] args) {
        List<Integer> li = List.of(1,2,3);
        System.out.println(li);

        // (num) -> num > 5; -> Compiler is very intellij that it is of type Predicate as it is accepting value and returning boolean
        // (num) -> num > 5; -> Is creating object behind the scenes of Predicate and assigned to pre by compiler
        Predicate<Integer> pre = (num) -> num > 5;

        // Fact Check: Only for objects we can check this. Which clearly indicates that 'pre' is object
        System.out.println(pre.getClass().getName());

        // Using the object 'pre' we are calling the method 'test' here
        System.out.println(pre.test(6));

        // => Inside the filter we are passing object only ( of type Predicate )
        // => but now we are not getting 'test' method, instead internally filter method will take this object
        // => *** And invoke test method which in turn calls lambda
        li.stream().filter(pre).forEach(num -> System.out.println(num));
    }
}
