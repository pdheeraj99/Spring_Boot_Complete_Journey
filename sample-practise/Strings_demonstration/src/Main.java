import java.util.concurrent.ConcurrentHashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String s1 = new String("Hello Dheeraj");
        String s2 = s1.concat(" Good Bye");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        StringBuilder s3 = new StringBuilder("Hello Dheeraj");
        StringBuilder s4 = s3.append(" Good Bye");
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());


    }
}