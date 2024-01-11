package SalaryIncrease;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String secondName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.age = age;
        this.salary = salary;
    }

    public void increaseSalary(double bonus) {
        if (this.getAge() < 30) {
            this.salary += (this.salary * bonus) / 200;
        } else {
            this.salary += (this.salary * bonus) / 100;
        }
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        DecimalFormat dc = new DecimalFormat("0.###");
        return String.format("%s %s gets %s leva"
                , this.firstName, this.lastName, dc.format(this.salary));
    }
}
