package restaurant;

import java.math.BigDecimal;

public class HotBeverage extends Beverage {
    // HotBeverage и ColdBeverage са напитки и при инициализация приемат следните параметри: String
    //name, BigDecimal price, double milliliters
    //Кафето и чаят са горещи напитки. Класът Coffee трябва да има следните допълнителни елементи:
    //• double COFFEE_MILLILITERS = 50
    //• BigDecimal COFFEE_PRICE = 3.50
    //• caffeine – double
    //• Getter метод за caffeine

    public HotBeverage(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}
