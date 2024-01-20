package SayHelloExtend;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Bulgarian("Peter"));
        persons.add(new European("Peter"));
        persons.add(new Chinese("Peter"));

        persons.forEach(e -> System.out.println(e.sayHello()));
    }
}
