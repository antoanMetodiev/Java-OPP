package Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Car:
        String[] vehicleTokens = scanner.nextLine().split("\\s+");
        VehicleImpl car = new Car(Double.parseDouble(vehicleTokens[1]), Double.parseDouble(vehicleTokens[2]));

        // Truck:
        vehicleTokens = scanner.nextLine().split("\\s+");
        VehicleImpl truck = new Truck(Double.parseDouble(vehicleTokens[1]), Double.parseDouble(vehicleTokens[2]));

        processesData(car, truck, scanner);
        // Print Vehicles:
        System.out.println(car);
        System.out.println(truck);
    }

    private static void processesData(VehicleImpl car, VehicleImpl truck, Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {

            String[] line = scanner.nextLine().split("\\s+");
            switch (line[0]) {
                case "Drive":
                    if (line[1].equals("Car")) {
                        System.out.println(car.drive(Double.parseDouble(line[2])));
                    } else {
                        System.out.println(truck.drive(Double.parseDouble(line[2])));
                    }
                    break;
                case "Refuel":
                    if (line[1].equals("Car")) {
                        car.refuel(Double.parseDouble(line[2]));
                    } else {
                        truck.refuel(Double.parseDouble(line[2]));
                    }
                    break;
            }
        }
    }
}
