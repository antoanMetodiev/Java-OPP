package VehiclesExtension;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleImpl car = new Car();
        VehicleImpl truck = new Truck();
        VehicleImpl bus = new Bus();

        fillVehicles(car, truck, bus, scanner);
        processesData(car, truck, bus, scanner);

        // Print Vehicles:
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }

    private static void processesData(VehicleImpl car, VehicleImpl truck, VehicleImpl bus, Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {

            String[] lineTokens = scanner.nextLine().split("\\s+");
            switch (lineTokens[0]) {
                case "Drive":
                    driveVehicle(car, truck, bus, lineTokens);
                    break;
                case "Refuel":
                    refuelVehicle(car, truck, bus, lineTokens);
                    break;
                case "DriveEmpty": // This Command is only for The Bus
                    bus.setFuelConsumption(bus.getFuelConsumption() - 1.4);
                    System.out.println(bus.drive(Double.parseDouble(lineTokens[2])));
                    bus.setFuelConsumption(bus.getFuelConsumption() + 1.4);
                    break;
            }
        }
    }

    private static void driveVehicle(VehicleImpl car, VehicleImpl truck, VehicleImpl bus, String[] lineTokens) {
        if (lineTokens[1].equals("Car")) {
            System.out.println(car.drive(Double.parseDouble(lineTokens[2])));
        } else if (lineTokens[1].equals("Truck")) {
            System.out.println(truck.drive(Double.parseDouble(lineTokens[2])));
        } else {
            System.out.println(bus.drive(Double.parseDouble(lineTokens[2])));
        }
    }

    private static void refuelVehicle(VehicleImpl car, VehicleImpl truck, VehicleImpl bus, String[] lineTokens) {
        if (lineTokens[1].equals("Car")) {
            car.refuel(Double.parseDouble(lineTokens[2]));
        } else if (lineTokens[1].equals("Truck")) {
            truck.refuel(Double.parseDouble(lineTokens[2]));
        } else {
            bus.refuel(Double.parseDouble(lineTokens[2]));
        }
    }

    private static void fillVehicles(VehicleImpl car, VehicleImpl truck, VehicleImpl bus, Scanner scanner) {
        // Car:
        String[] vehicleTokens = scanner.nextLine().split("\\s+");
        car.setFuelQuantity(Double.parseDouble(vehicleTokens[1]));
        car.setFuelConsumption(Double.parseDouble(vehicleTokens[2]) + 0.9);
        car.setTankCapacity(Double.parseDouble(vehicleTokens[3]));

        // Truck:
        vehicleTokens = scanner.nextLine().split("\\s+");
        truck.setFuelQuantity(Double.parseDouble(vehicleTokens[1]));
        truck.setFuelConsumption(Double.parseDouble(vehicleTokens[2]) + 1.6);
        truck.setTankCapacity(Double.parseDouble(vehicleTokens[3]));

        // Bus:
        vehicleTokens = scanner.nextLine().split("\\s+");
        bus.setFuelQuantity(Double.parseDouble(vehicleTokens[1]));
        bus.setFuelConsumption(Double.parseDouble(vehicleTokens[2]) + 1.4);
        bus.setTankCapacity(Double.parseDouble(vehicleTokens[3]));
    }
}
