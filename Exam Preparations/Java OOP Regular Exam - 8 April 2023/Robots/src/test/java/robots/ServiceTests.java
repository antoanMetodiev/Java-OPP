package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTests {
    private Service service;
    private String EXPECTED_NAME = "Lilyan";
    private int EXPECTED_CAPACITY = 5;

    @Before
    public void setup() {
        this.service = new Service(EXPECTED_NAME, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_IfReceiveInvalidName_Null_Throw() {
        service = new Service(null, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_IfReceiveInvalidName_WhiteSpaces_Throw() {
        service = new Service("  ", EXPECTED_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_IfReceiveInvalidCapacity_Throw() {
        service = new Service(EXPECTED_NAME, -1);
    }

    @Test
    public void test_Constructor_ShouldWorkCorrect() {
        Assert.assertEquals(this.EXPECTED_CAPACITY, this.service.getCapacity());
        Assert.assertEquals(this.EXPECTED_NAME, this.service.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddM_IfDoNotHaveCapacity_Throw() {
        for (int i = 0; i < EXPECTED_CAPACITY + 1; i++) {
            this.service.add(new Robot("RandomName"));
        }
    }

    @Test
    public void test_AddM_ShouldWorkCorrect() {
        int sizeBeforeAdd = this.service.getCount();
        this.service.add(new Robot("RandomRobot"));
        Assert.assertEquals(sizeBeforeAdd + 1, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveM_IfReceiveInvalidName() {
        this.service.remove("RandomName");
    }

    @Test
    public void test_RemoveM_ShouldWorkCorrect() {
        this.service.add(new Robot("Sasho"));
        int sizeBeforeRemove = this.service.getCount();
        this.service.remove("Sasho");
        Assert.assertEquals(sizeBeforeRemove - 1, this.service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ForSaleM_IfReceiveInvalidName_Throw() {
        this.service.forSale("InvalidName");
    }

    @Test
    public void test_ForSaleM_ShouldWorkCorrect() {
        Robot EXPECTED_ROBOT = new Robot("Ivan");
        this.service.add(EXPECTED_ROBOT);
        Robot receivedRobot = this.service.forSale("Ivan");
        Assert.assertEquals(EXPECTED_ROBOT.getName(), receivedRobot.getName());
        Assert.assertFalse(receivedRobot.isReadyForSale());
    }

    @Test
    public void test_ReportM_ShouldWorkCorrect() {
        this.service.add(new Robot("Sashko"));
        String EXPECTED_TEXT = "The robot Sashko is in the service Lilyan!";
        Assert.assertEquals(EXPECTED_TEXT, this.service.report());
    }
}
