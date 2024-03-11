package handball.entities.equipment;

public abstract class BaseEquipment implements Equipment {
    private final int protection;
    private final double price;

    public BaseEquipment(int protection, double price) {
        this.protection = protection;
        this.price = price;
    }

    @Override
    public int getProtection() {
        return this.protection;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
