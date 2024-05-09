package climbers.models.climber;

public class WallClimber extends BaseClimber {
    private static final double INITIAL_STRENGTH = 90;

    public WallClimber(String name) {
        super(name, INITIAL_STRENGTH);
    }

    @Override
    public void climb() {
        double result = this.getStrength() - 30;
        if (result < 0) {
            this.setStrength(0);
        } else {
            this.setStrength(result);
        }
    }
}
