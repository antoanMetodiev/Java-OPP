package Vehicles;

import java.text.DecimalFormat;

public class VehicleImpl implements Vehicle {
    private double fuelQuantity; // per KM
    private double fuelConsumption;  // per KM

    public VehicleImpl(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuelQuantity(double fuelQuantity) {
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
        this.fuelQuantity += refuelLiters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
