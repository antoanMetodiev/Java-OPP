package VehiclesExtension;

public class Bus extends VehicleImpl {
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public Bus() {
        super(1, 1, 1);
    }
}
