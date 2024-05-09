package christmasPastryShop.entities.delicacies.interfaces;

public class Stolen extends BaseDelicacy{
    private static final double PORTION = 250;

    public Stolen(String name, double price) {
        super(name, PORTION, price);
    }
}
