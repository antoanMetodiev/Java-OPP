package easterRaces.entities.cars;

import easterRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar {
    private static final double CUBIC_CM = 3000;
    private static final int MIN_HORSE_POWER = 250;
    private static final int MAX_HORSE_POWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CM);
        setHorsePower(horsePower);
    }

    private void setHorsePower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
    }

}
