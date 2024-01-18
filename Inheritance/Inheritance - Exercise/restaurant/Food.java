package restaurant;

import java.math.BigDecimal;

public class Food extends Product {
    // Класът Food трябва да има следните елементи:
    //• Конструктор със следните параметри: String name, BigDecimal price, double grams
    //• name – String
    //• price – double
    //• grams - double
    //• Getter метод за grams

    private double grams;

    public Food(String name, BigDecimal price, double grams) {
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }
}
