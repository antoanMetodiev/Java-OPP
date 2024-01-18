package restaurant;

import java.math.BigDecimal;

public class Beverage extends Product {
    // Публичен конструктор със следните параметри: String name, BigDecimal price, double milliliters
    //• name – String
    //• price – BigDecimal
    //• milliliters - double
    //• Getter метод за milliliters
    private double milliliters;
    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
