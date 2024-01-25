package VehiclesExtension;

import java.text.DecimalFormat;

public class VehicleImpl implements Vehicle {
    private double fuelQuantity; // per KM
    private double fuelConsumption;  // per KM
    private double tankCapacity;

    public VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setFuelQuantity(double fuelQuantity) {
//        if (fuelQuantity <= 0) {
//            System.out.println("");
//        } else {
//            this.fuelQuantity = fuelQuantity;
//        }\
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String drive(double km) {
        double neededFuel = km * this.fuelConsumption;
        if (neededFuel <= this.fuelQuantity) {
            DecimalFormat df = new DecimalFormat("0.##");
            this.fuelQuantity -= neededFuel;
            return String.format("%s travelled %s km", super.getClass().getSimpleName(), df.format(km));
        }
        return String.format("%s needs refueling", this.getClass().getSimpleName());
    }

    @Override
    public void refuel(double refuelLiters) {
        if (refuelLiters <= 0) {
            System.out.println("Fuel must be a positive number");
        } else if (this.fuelQuantity + refuelLiters > this.tankCapacity) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            this.fuelQuantity += refuelLiters;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
