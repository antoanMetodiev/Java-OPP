package FoodShortage;

import FoodShortage.Buyer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Buyer> peopleCollector = new LinkedHashMap<>();
        fillMapWithPersons(peopleCollector, scanner);

        checkMapContainKeys(peopleCollector, scanner);
        printOutput(peopleCollector);
    }

    private static void printOutput(Map<String,Buyer> peopleCollector) {
        int totalBoughtFood = 0;
        for (Buyer value : peopleCollector.values()) {
            totalBoughtFood += value.getFood();
        }
        System.out.println(totalBoughtFood);
    }

    private static void checkMapContainKeys(Map<String,Buyer> peopleCollector, Scanner scanner) {
        String line = scanner.nextLine();
        int totalBoughtFood = 0;
        while (!"End".equals(line)) {
            if (peopleCollector.containsKey(line)) {
                // TODO: increase food field
                peopleCollector.get(line).buyFood();
            }
            line = scanner.nextLine();
        }
    }

    private static void fillMapWithPersons(Map<String, Buyer> peopleCollector, Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {

            String[] tokens = scanner.nextLine().split("\\s+");
            switch (tokens.length) {
                case 4:
                    peopleCollector.put(tokens[0], new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]));
                    break;
                case 3:
                    peopleCollector.put(tokens[0], new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]));
                    break;
            }
        }
    }
}
