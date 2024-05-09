package CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String card = scanner.nextLine();
        String suit = scanner.nextLine();

        CardName cardName1 = CardName.valueOf(card);
        CardType cardType1 = CardType.valueOf(suit);
        System.out.printf("Card name: %s of %s; Card power: %d"
                , card, suit, cardName1.getPower() + cardType1.getValue());
    }
}
