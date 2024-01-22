package Person;

public class Citizen implements Person {

    // - name: String
    //- age: int
    //+ Citizen (String, int)
    //+ getName() : String
    //+ getAge() : int

    private String name;
    private int age;

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
