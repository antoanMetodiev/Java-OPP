package vehicleShop.models.worker;

public class SecondShift extends BaseWorker {
    private static final int INITIAL_STRENGTH = 70;

    public SecondShift(String name) {
        super(name, INITIAL_STRENGTH);
    }

    @Override
    public void working() {
        int value = this.getStrength() - 15;
        if (value < 0) {
            this.setStrength(0);
        } else {
            this.setStrength(value);
        }
    }
}
