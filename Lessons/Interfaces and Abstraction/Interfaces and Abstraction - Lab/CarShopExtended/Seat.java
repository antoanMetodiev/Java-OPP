package CarShopExtended;

import CarShop.Car;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    public Seat(String model, String color, Integer horsePower, String country, Double price) {
        super(model, color, horsePower, country);
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires\n" +
                        "%s sells for %.6f",
                super.getModel(),
                super.countryProduced(), Car.TIRES, super.getModel(), this.getPrice());
    }
}
