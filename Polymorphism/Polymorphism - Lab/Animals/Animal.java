package Animals;

public abstract class Animal {
    // ⦁	name: String
    //⦁	favouriteFood: String

    private String name;
    private String favouriteFood;

    protected Animal(String name, String favouriteFood) {
        this.name = name;
        this.favouriteFood = favouriteFood;
    }

    public String getName() {
        return name;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }

    // explainSelf(): String.

    public abstract String explainSelf();

}
