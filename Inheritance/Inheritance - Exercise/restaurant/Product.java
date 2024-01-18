package restaurant;

import java.math.BigDecimal;

public class Product {
    //  Публичен конструктор със следните параметри: String name, BigDecimal price
    //• name – String
    //• price – BigDecimal
    //• Getter методи за полетата

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
