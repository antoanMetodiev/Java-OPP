package easterRaces.entities.cars;

import easterRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {
    private static final double CUBIC_CM = 5000;
    private static final int MIN_HORSE_POWER = 400;
    private static final int MAX_HORSE_POWER = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CM);
        setHorsePower(horsePower);
    }

    private void setHorsePower(int horsePower) {
        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
    }
}
