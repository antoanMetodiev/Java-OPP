package Vehicles;

public class Truck extends VehicleImpl {
    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        super.setFuelConsumption(fuelConsumption + 1.6);
    }

    @Override
    public void refuel(double refuelLiters) {
        super.setFuelQuantity(super.getFuelQuantity() + (refuelLiters * 0.95));
    }
}
