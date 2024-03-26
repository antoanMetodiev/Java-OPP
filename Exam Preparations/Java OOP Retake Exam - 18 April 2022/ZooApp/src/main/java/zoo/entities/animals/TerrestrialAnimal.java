package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {
    private static final double INITIAL_KG = 5.50;
    private static final double INCREASE_KG = 5.70;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eat() {
        this.setKg(this.getKg() + INCREASE_KG);
    }
}
