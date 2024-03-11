package handball.entities.team;

public class Germany extends BaseTeam {
    private static final int ADDITIONAL_POINTS = 145;

    public Germany(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        this.setAdvantage(this.getAdvantage() + ADDITIONAL_POINTS);
    }
}
