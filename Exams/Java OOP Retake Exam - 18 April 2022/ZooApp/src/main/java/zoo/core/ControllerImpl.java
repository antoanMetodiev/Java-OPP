package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area = null;
        if (areaType.equals("LandArea")) {
            area = new LandArea(areaName);
        } else if (areaType.equals("WaterArea")) {
            area = new WaterArea(areaName);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }

        this.areas.add(area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food = null;
        if (foodType.equals("Meat")) {
            food = new Meat();
        } else if (foodType.equals("Vegetable")) {
            food = new Vegetable();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }

        this.foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Area area = this.findByAreaName(areaName);
        Food food = this.foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        }

        this.foodRepository.remove(food);
        area.addFood(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Area area = this.findByAreaName(areaName);
        Animal animal = null;
        if (animalType.equals("AquaticAnimal")) {
            animal = new AquaticAnimal(animalName, kind, price);
        } else if (animalType.equals("TerrestrialAnimal")) {
            animal = new TerrestrialAnimal(animalName, kind, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }

        if (animalType.equals("AquaticAnimal") && area.getClass().getSimpleName().equals("WaterArea")) {
            area.addAnimal(animal); 
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
        } else if (animalType.equals("TerrestrialAnimal") && area.getClass().getSimpleName().equals("LandArea")) {
            area.addAnimal(animal);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
        }
        return ConstantMessages.AREA_NOT_SUITABLE;
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = findByAreaName(areaName);
        area.feed();
        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = findByAreaName(areaName);
        double totalKg = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, totalKg);
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder();
        this.areas.forEach(e -> result.append(e.getInfo()));
        return result.toString().trim();
    }

    private Area findByAreaName(String areaName) {
        return this.areas.stream().filter(e -> e.getName().equals(areaName)).findFirst().orElse(null);
    }
}
