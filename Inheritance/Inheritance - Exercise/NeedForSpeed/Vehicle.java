package NeedForSpeed;

public class Vehicle {
    // • DEFAULT_FUEL_CONSUMPTION – final static double (константа)
    //• fuelConsumption – double
    //• fuel – double
    //• horsePower – int
    //• Методи за връщане и задаване на стойностите на полетата
    //• Публичен конструктор, който приема (fuel, horsePower)
    // и задава стойността на полето fuelConsumption със стойността по подразбиране
    //• void drive(double kilometers)


    private static final double DEFAULT_FUEL_CONSUMPTION = 1.25;
    private double fuelConsumption;
    private double fuel;
    private int horsePower;

    public Vehicle(double fuel, int horsePower) {
        this.fuel = fuel;
        this.horsePower = horsePower;
        this.fuelConsumption = Vehicle.DEFAULT_FUEL_CONSUMPTION;
    }

    public void drive(double kilometers) {
        double decreasedFuel = kilometers * this.fuelConsumption;
        if (this.fuel - decreasedFuel >= 0) {
            this.fuel -= decreasedFuel;
        }
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
