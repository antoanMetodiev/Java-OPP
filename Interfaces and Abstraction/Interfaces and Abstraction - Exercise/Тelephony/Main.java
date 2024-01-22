package Тelephony;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Smartphone smartphone = new Smartphone
                (Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList()),
                Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList()));

        printValidPhoneNumbers(smartphone);
        printValidUrls(smartphone);
    }

    private static void printValidUrls(Smartphone smartphone) {
        while (!smartphone.getUrls().isEmpty()) {
            System.out.println(smartphone.browse());
            smartphone.getUrls().remove(0);
        }
    }

    private static void printValidPhoneNumbers(Smartphone smartphone) {
        while (!smartphone.getNumbers().isEmpty()) {
            System.out.println(smartphone.call());
            smartphone.getNumbers().remove(0);
        }
    }
}
