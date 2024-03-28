package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CarShopTests {

    private CarShop carShop;

    @Before
    public void setup() {
        this.carShop = new CarShop();
    }

    @Test
    public void test_FindAllCarsWithMaxHorsePower() {
        this.carShop.add(new Car("Random Model1", 5, 1.50));
        this.carShop.add(new Car("Random Model2", 6, 1.50));

        int NEEDED_HORSEPOWER = 4;
        List<Car> EXPECTED_LIST = this.carShop.getCars().stream()
                .filter(c -> c.getHorsePower() > NEEDED_HORSEPOWER).collect(Collectors.toList());

        List<Car> returnedList = this.carShop.findAllCarsWithMaxHorsePower(NEEDED_HORSEPOWER);

        Assert.assertEquals(EXPECTED_LIST.size(), returnedList.size());
        boolean theyEquals = true;

        for (int i = 0; i < EXPECTED_LIST.size(); i++) {
            if (!EXPECTED_LIST.get(i).equals(returnedList.get(i))) {
                theyEquals = false;
            }
        }
        Assert.assertTrue(theyEquals);
    }

    @Test(expected = NullPointerException.class)
    public void test_AddM_IfReceiveNull_Throw() {
        this.carShop.add(null);
    }

    @Test
    public void test_AddM_ShouldWorkCorrect() {
        this.carShop.add(new Car("Random Model", 1, 1));
        Assert.assertEquals(this.carShop.getCars().size(), 1);
        Assert.assertEquals(this.carShop.getCars().get(0).getModel(), "Random Model");
        Assert.assertEquals(this.carShop.getCars().get(0).getHorsePower(), 1);
    }

    @Test
    public void test_RemoveM_ShouldWorkCorrect() {
        Car carForRemove = new Car("Random Model", 1, 1);
        this.carShop.add(new Car("Random Model", 1, 2));
        this.carShop.add(carForRemove);

        this.carShop.remove(carForRemove);
        Assert.assertEquals(this.carShop.getCount(), 1);
    }

    @Test
    public void test_GetTheMostLuxuryCar_IfListIsEmpty() {
        Assert.assertNull(this.carShop.getTheMostLuxuryCar());
    }

    @Test
    public void test_GetTheMostLuxuryCar_ShouldWorkCorrect() {
        Car car = new Car("Random Model", 1, 2);
        this.carShop.add(new Car("Random Model", 1, 1));
        this.carShop.add(car);

        Assert.assertEquals(this.carShop.getTheMostLuxuryCar(), car);
    }

    @Test
    public void test_findAllCarByModel_ShouldWorkCorrect() {
        Car car = new Car("Random Model", 1, 2);
        this.carShop.add(new Car("Random Model", 1, 1));
        this.carShop.add(car);

        List<Car> receivedCars = this.carShop.findAllCarByModel("Random Model");
        Assert.assertEquals(receivedCars.size(), 2);
    }
}

