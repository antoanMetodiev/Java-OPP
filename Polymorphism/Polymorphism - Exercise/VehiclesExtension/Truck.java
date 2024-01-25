package VehiclesExtension;

public class Truck extends VehicleImpl {

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        super.setFuelConsumption(fuelConsumption);
    }

    public Truck() {
        super(1, 1, 1);
    }

    @Override
    public void refuel(double refuelLiters) {
        if (refuelLiters <= 0) {
            System.out.println("Fuel must be a positive number");
        } else if (super.getFuelQuantity() + refuelLiters > this.getTankCapacity()) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            super.setFuelQuantity(super.getFuelQuantity() + (refuelLiters * 0.95));
        }
    }
}
