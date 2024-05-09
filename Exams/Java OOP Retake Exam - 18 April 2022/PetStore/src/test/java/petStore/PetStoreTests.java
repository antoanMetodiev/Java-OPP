package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {
    private PetStore petStore;

    @Before
    public void setup() {
        petStore = new PetStore();
    }

    @Test
    public void test_FindAllAnimalsWithMaxKilograms_ShouldWorkCorrect() {
        int EXPECTED_MAX_KG = 15;
        this.petStore.addAnimal(new Animal("Fishes", EXPECTED_MAX_KG, 1.50));
        this.petStore.addAnimal(new Animal("Birds", EXPECTED_MAX_KG, 1.50));
        List<Animal> allAnimalsWithMaxKilograms = petStore.findAllAnimalsWithMaxKilograms(EXPECTED_MAX_KG);

        boolean isCorrect = true;
        for (Animal animal : allAnimalsWithMaxKilograms) {
            if (animal.getMaxKilograms() != EXPECTED_MAX_KG) {
                isCorrect = false;
            }
        }
        Assert.assertTrue(isCorrect);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal_IfReceiveNull_Throw() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_GetTheMostExpensiveAnimal_ListIsEmpty_ShouldReturnNull() {
        Animal mostExpensiveAnimal = petStore.getTheMostExpensiveAnimal();
        Assert.assertNull(mostExpensiveAnimal);
    }

    @Test
    public void test_GetTheMostExpensiveAnimal_ShouldWorkCorrect() {
        Animal mostExpensiveAnimal = new Animal("Random Specie2", 12345, 3);
        this.petStore.addAnimal(new Animal("Random Specie1", 1234, 1));
        this.petStore.addAnimal(new Animal("Random Specie2", 12345, 2));
        this.petStore.addAnimal(mostExpensiveAnimal);
        Animal receivedResult = petStore.getTheMostExpensiveAnimal();

        Assert.assertSame(mostExpensiveAnimal, receivedResult);
    }

    @Test
    public void test_findAllAnimalBySpecie_ShouldWorkCorrect() {
        String EXPECTED_SPECIE = "Fish";
        this.petStore.addAnimal(new Animal(EXPECTED_SPECIE, 1, 1));
        this.petStore.addAnimal(new Animal(EXPECTED_SPECIE, 2, 2));

        List<Animal> allAnimalBySpecie = this.petStore.findAllAnimalBySpecie(EXPECTED_SPECIE);
        int EXPECTED_SIZE = this.petStore.getCount();
        Assert.assertEquals(this.petStore.getAnimals().size(), EXPECTED_SIZE);

        boolean flag = true;
        for (Animal animal : allAnimalBySpecie) {
            if (!animal.getSpecie().equals(EXPECTED_SPECIE)) {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }
}
