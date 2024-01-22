package FoodShortage;

public class Rebel implements Person, Buyer {

    // - name: String
    //- age: int
    //- group: String
    //- food: int
    // + getGroup(): String
    private String name;
    private int age;
    private String group;
    private int food = 0;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public void buyFood() {
        // TODO
        this.food += 5;
    }

    @Override
    public int getFood() {
        return this.food;
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
