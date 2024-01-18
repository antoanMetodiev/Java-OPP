package restaurant;

import java.math.BigDecimal;

public class Cake extends Dessert {
    // Класът Cake трябва да има следните елементи при инициализация:
    //• double CAKE_GRAMS = 250
    //• double CAKE_CALORIES = 1000
    //• BigDecimal CAKE_PRICE = 5

    private static final double CAKE_GRAMS = 250;
    private static final double CAKE_CALORIES = 1000;
    private static final BigDecimal CAKE_PRICE = new BigDecimal(5);

    public Cake(String name) {
        super(name, CAKE_PRICE, CAKE_GRAMS, CAKE_CALORIES);
    }

}
