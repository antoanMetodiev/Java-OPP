package busyWaiters;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class RestaurantTests {
    private Restaurant restaurant;
    private static final String EXPECTED_NAME = "Lilyan";
    private static final int EXPECTED_CAPACITY = 2;

    @Before
    public void setup() {
        this.restaurant = new Restaurant(EXPECTED_NAME, EXPECTED_CAPACITY);
    }

    @Test
    public void test_Constructor_ShouldWorkCorrect() {
        Assert.assertEquals(this.restaurant.getName(), EXPECTED_NAME);
        Assert.assertEquals(this.restaurant.getCapacity(), EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetNameM_IfReceiveNull_Throw() {
        this.restaurant = new Restaurant(null, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetNameM_IfReceiveEmptySpaces_Throw() {
        this.restaurant = new Restaurant("  ", EXPECTED_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SetCapacityM_IfReceiveNegativeNumber_Throw() {
        this.restaurant = new Restaurant(EXPECTED_NAME, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddFullTimeWaiterM_IfNotHaveCapacity_Throw() {
        this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name1", 1));
        this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name2", 2));
        this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name3", 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddFullTimeWaiterM_IfAddCountainingWaiter_Throw() {
        this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name1", 1));
        this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name1", 1));
    }

    @Test
    public void test_AddFullTimeWaiterM_ShouldWorkCorrect() {
        int sizeBeforeAdd = this.restaurant.getCount();
        this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name1", 1));
        Assert.assertEquals(sizeBeforeAdd + 1, this.restaurant.getCount());
        Collection<FullTimeWaiter> waiters = this.restaurant.getWaiters();
        Assert.assertEquals(waiters.iterator().next().getName(), "Random Name1");
        Assert.assertEquals(waiters.iterator().next().getEfficiency(), 1);
    }

    @Test
    public void test_RemoveFullTimeWaiter_ShouldWorkCorrect() {
       this.restaurant.addFullTimeWaiter(new FullTimeWaiter("Random Name", 1));
       int sizeBeforeRemove = this.restaurant.getCount();
       Assert.assertTrue(this.restaurant.removeFullTimeWaiter("Random Name"));
    }
}
