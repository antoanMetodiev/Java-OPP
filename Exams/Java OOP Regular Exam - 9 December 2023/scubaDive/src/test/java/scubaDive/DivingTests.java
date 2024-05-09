package scubaDive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivingTests {
    private Diving diving;
    private static final String EXPECTED_NAME = "Vasil";
    private static final int EXPECTED_CAPACITY = 2;

    @Before
    public void setup() {
        this.diving = new Diving(EXPECTED_NAME, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_IfNameIsNull_Throw() {
        this.diving = new Diving(null, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_IfNameIsWhiteSpaces_Throw() {
        this.diving = new Diving("   ", EXPECTED_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_IfReceiveInvalidCapacity_Throw() {
        this.diving = new Diving(EXPECTED_NAME, -1);
    }

    @Test
    public void test_Constructor_ShouldCreateCorrect() {
        Assert.assertEquals(this.diving.getName(), EXPECTED_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addDeepWaterDiver_IfCapacityIsEqualsOnCollectionSize_Throw() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person1", 1));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person2", 2));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person3", 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addDeepWaterDiver_IfReceiveContainingDiver_Throw() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person1", 1));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person1", 1));
    }

    @Test
    public void test_addDeepWaterDiver_ShouldWorkCorrect() {
        int initialSize = this.diving.getCount();
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person1", 1));
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random Person2", 2));
        int sizeAfterAdd = this.diving.getCount();
        Assert.assertEquals(initialSize + 2, sizeAfterAdd);
    }

    @Test
    public void test_removeDeepWaterDiver_IfReceiveNotContainedDiver() {
        Assert.assertFalse(this.diving.removeDeepWaterDiver("Random"));
    }

    @Test
    public void test_removeDeepWaterDiver_ShouldWorkCorrect() {
        this.diving.addDeepWaterDiver(new DeepWaterDiver("Random", 1));
        int sizeBeforeRemove = this.diving.getCount();
        Assert.assertTrue(this.diving.removeDeepWaterDiver("Random"));
        int sizeAfterRemove = this.diving.getCount();
        Assert.assertEquals(sizeBeforeRemove, sizeAfterRemove + 1);
    }
}
