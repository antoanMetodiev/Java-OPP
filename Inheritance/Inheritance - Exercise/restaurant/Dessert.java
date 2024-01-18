package restaurant;

import java.math.BigDecimal;

public class Dessert extends Food {
    // Класът Dessert трябва да приема още един параметър в конструктора си: double
    //calories.
    //• calories – double
    //• Getter метод за calories
    private double calories;

    public Dessert(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams);
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
