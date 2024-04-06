package restaurant.models.waiter;

public class FullTimeWaiter extends BaseWaiter{

    public FullTimeWaiter(String name) {
        super(name, 8);
    }

    @Override
    public void work() {
        int value = this.getEfficiency() - 1;
        if (value < 0) {
            this.setEfficiency(0);
        } else {
            this.setEfficiency(value);
        }
    }
}
