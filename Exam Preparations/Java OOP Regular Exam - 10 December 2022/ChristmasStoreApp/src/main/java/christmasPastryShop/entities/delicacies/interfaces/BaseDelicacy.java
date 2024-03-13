package christmasPastryShop.entities.delicacies.interfaces;

import christmasPastryShop.common.ExceptionMessages;

public abstract class BaseDelicacy implements Delicacy {
    private String name;
    private double portion;
    private double price;

    public BaseDelicacy(String name, double portion, double price) {
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            // "Name cannot be null or white space!"
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    private void setPortion(double portion) {
        if (portion <= 0) {
            // "Portion cannot be less or equal to zero!"
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            // "Price cannot be less or equal to zero!"
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        // "{current delicacy name}: {current portion - formatted to the second digit}g - {current price - formatted to the second digit}"
        return String.format("%s: %.2fg" +
                " - %.2f%n", this.getName(), this.portion, this.price);
    }
}
