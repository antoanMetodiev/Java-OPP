package CardsWithPower;

public enum CardType {
    CLUBS(0), DIAMONDS(13),
    HEARTS(26), SPADES(39);

    private final int value;

    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
