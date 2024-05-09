package VehiclesExtension;

public class Car extends VehicleImpl {

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public Car() {
        super(1, 1,1);
    }
}
