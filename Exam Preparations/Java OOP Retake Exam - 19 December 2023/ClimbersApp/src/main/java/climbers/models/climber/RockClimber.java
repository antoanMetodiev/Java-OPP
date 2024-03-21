package climbers.models.climber;

public class RockClimber extends BaseClimber {
    private static final double INITIAL_STRENGTH = 120;

    public RockClimber(String name) {
        super(name, INITIAL_STRENGTH);
    }

    @Override
    public void climb() {
        double result = this.getStrength() - 60;
        if (result < 0) {
            this.setStrength(0);
        } else {
            this.setStrength(result);
        }
    }
}
