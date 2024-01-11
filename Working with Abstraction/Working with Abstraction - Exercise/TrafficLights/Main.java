package TrafficLights;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] lights = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][lights.length];
        fillMatrix(matrix, n, lights);
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            Arrays.stream(strings).forEach(e -> System.out.print(e + " "));
            System.out.println();
        }
    }

    private static void fillMatrix(String[][] matrix, int n, String[] lights) {
        for (int i = 0; i < lights.length; i++) {
            String currentLight = "";
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    matrix[j][i] = Light.valueOf(lights[i]).getLightValue();
                    currentLight = Light.valueOf(lights[i]).getLightValue();
                    continue;
                }
                matrix[j][i] = Light.valueOf(currentLight).getLightValue();
                currentLight = Light.valueOf(currentLight).getLightValue();
            }
        }
    }
}
