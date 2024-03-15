package easterRaces.core.interfaces;

import easterRaces.common.ExceptionMessages;
import easterRaces.common.OutputMessages;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.CarRepository;
import easterRaces.repositories.interfaces.DriverRepository;
import easterRaces.repositories.interfaces.RaceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private CarRepository carRepository;
    private DriverRepository driverRepository;
    private RaceRepository raceRepository;

    public ControllerImpl() {
        this.carRepository = new CarRepository();
        this.driverRepository = new DriverRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driverName) {
        DriverImpl driver = new DriverImpl(driverName);

        Driver receivedDriver = this.driverRepository.getByName(driverName);

        if (receivedDriver != null) {
            // "Driver {name} is already created."
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }
        this.driverRepository.add(driver);
        return String.format(String.format(OutputMessages.DRIVER_CREATED, driverName));
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null;
        String carType = "";
        if (type.startsWith("Muscle")) {
            car = new MuscleCar(model, horsePower);
            carType = "MuscleCar";
        } else if (type.startsWith("Sports")) {
            car = new SportsCar(model, horsePower);
            carType = "SportsCar";
        }

        Car foundedCar = this.carRepository.getAll().stream()
                .filter(e -> e.getModel().equals(model))
                .findFirst().orElse(null);

        if (foundedCar == null) {
            this.carRepository.add(car);
            return String.format(OutputMessages.CAR_CREATED, carType, model);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        // първо проверяваме дали шофъорa съществува!
        Driver driver = this.driverRepository.getByName(driverName);

        if (driver == null) {
            //  "Driver {name} could not be found."
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        // след това дали колата съществува!
        Car car = this.carRepository.getAll()
                .stream().filter(e -> e.getModel().equals(carModel))
                .findFirst().orElse(null);

        if (car == null) {
            // "Car {name} could not be found."
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        // "Driver {driver name} received car {car name}."
        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        // Първо проверяваме дали race съществува!
        Race race = this.raceRepository.getByName(raceName);

        if (race == null) {
            // "Race {name} could not be found."
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        // След това дали driver съществува
        Driver driver = this.driverRepository.getByName(driverName);

        if (driver == null) {
            // "Driver {name} could not be found."
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        // добавяме колата в състезанието!
        race.addDriver(driver);

        // "Driver {driver name} added in {race name} race."
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }

        // взимаме си най-бързите 3ма състезателя, според техния calculateRacePoints() резултат!
        List<Driver> fastestDrivers = race.getDrivers().stream().sorted((e1, e2) -> Double.compare(e2.getCar()
                        .calculateRacePoints(race.getLaps()),
                e1.getCar().calculateRacePoints(race.getLaps()))).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, fastestDrivers.get(0).getName(), race.getName())).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION, fastestDrivers.get(1).getName(), race.getName())).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION, fastestDrivers.get(2).getName(), race.getName())).append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        if (this.raceRepository.getByName(name) == null) {
            this.raceRepository.add(new RaceImpl(name, laps));
            // "Race {name} is created."
            return String.format(OutputMessages.RACE_CREATED, name);
        }
        // "Race {name} is already created."
        throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
    }
}
