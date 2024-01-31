import java.util.Scanner;

public class NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String bounds = scanner.nextLine();
        int startBound = Integer.parseInt(bounds.split(" ")[0]);
        int endBound = Integer.parseInt(bounds.split(" ")[1]);
        System.out.printf("Range: [%d...%d]\n", startBound, endBound);

        String line = scanner.nextLine();
        while (true) {
            try {
                int num = Integer.parseInt(line);
                if (num >= startBound && num <= endBound) {
                    System.out.println("Valid number: " + num);
                    return;
                } else {
                    System.out.println("Invalid number: " + line);
                }

            } catch (NumberFormatException ex) {
                System.out.println("Invalid number: " + line);
            }
            line = scanner.nextLine();
        }
    }
}
