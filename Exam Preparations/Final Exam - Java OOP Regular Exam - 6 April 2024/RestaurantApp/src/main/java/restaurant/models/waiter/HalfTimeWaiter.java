package restaurant.models.waiter;

public class HalfTimeWaiter extends BaseWaiter {

    public HalfTimeWaiter(String name) {
        super(name, 4);
    }

    @Override
    public void work() {
        int value = this.getEfficiency() - 2;
        if (value < 0) {
            this.setEfficiency(0);
        } else {
            this.setEfficiency(value);
        }
    }
}
