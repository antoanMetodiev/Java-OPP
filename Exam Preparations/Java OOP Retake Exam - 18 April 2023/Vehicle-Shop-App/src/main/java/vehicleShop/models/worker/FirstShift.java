package vehicleShop.models.worker;

public class FirstShift extends BaseWorker {
    private static final int INITIAL_STRENGTH = 100;

    public FirstShift(String name) {
        super(name, INITIAL_STRENGTH);
    }

    @Override
    public void working() {
        int value = this.getStrength() - 10;
        if (value < 0) {
            this.setStrength(0);
        } else {
            this.setStrength(value);
        }
    }
}
