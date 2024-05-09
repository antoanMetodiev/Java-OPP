import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Integer> validNumbers = new ArrayList<>();
        int validNumbersCounter = 0;
        int previousBigNumber = 1;
        while (validNumbersCounter != 10) {
        
            String line = scanner.nextLine();
            try {
                int number = Integer.parseInt(line);
                if (number > previousBigNumber && number < 100) {
                    validNumbersCounter++;
                    previousBigNumber = number;
                    validNumbers.add(number);
                } else {
                    System.out.printf("Your number is not in range %d - 100!\n", previousBigNumber);
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid Number!");
            }
        }
        // Output:
        System.out.println(String.join(", ", validNumbers.toString()
                .replaceAll("[\\[\\]]", "")));
    }
}
