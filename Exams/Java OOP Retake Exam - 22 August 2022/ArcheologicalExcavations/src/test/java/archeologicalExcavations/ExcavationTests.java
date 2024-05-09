package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {
    private Excavation excavation;
    private static final String EXPECTED_NAME = "Larry";
    private static final int EXPECTED_CAPACITY = 3;
    private static final Archaeologist ARCHAEOLOGIST = new Archaeologist("Kobi", 15);

    @Before
    public void setup() {
        excavation = new Excavation(EXPECTED_NAME, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameM_IfReceiveNullShouldThrow() {
        excavation = new Excavation(null, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameM_IfReceiveEmptySpasesShouldThrow() {
        excavation = new Excavation("  ", EXPECTED_CAPACITY);
    }

    @Test
    public void testSetNameM_ShouldWorkCorrect() {
        Assert.assertEquals(EXPECTED_NAME, excavation.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityM_IfReceiveInvalidCapacity_Throw() {
        excavation = new Excavation(EXPECTED_NAME, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddM_IfSizeIsEqualOfCapacity_Throw() {
        excavation.addArchaeologist(ARCHAEOLOGIST);
        excavation.addArchaeologist(new Archaeologist("Random1", 1));
        excavation.addArchaeologist(new Archaeologist("Random2", 2));
        excavation.addArchaeologist(new Archaeologist("Random3", 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddM_IfCountainsEl_ShouldThrow() {
        excavation.addArchaeologist(ARCHAEOLOGIST);
        excavation.addArchaeologist(ARCHAEOLOGIST);
    }

    @Test
    public void testAddM_ShouldWorkCorrect() {
        int sizeBeforeAddedEl = excavation.getCount();
        excavation.addArchaeologist(ARCHAEOLOGIST);
        Assert.assertEquals(sizeBeforeAddedEl + 1, excavation.getCount());
    }

    @Test
    public void testRemoveM_IfReceiveInvalidName_ShouldReturnFalse() {
        boolean result = excavation.removeArchaeologist("Anq");
        Assert.assertFalse(result);
    }

    @Test
    public void testRemoveM_ShouldWorkCorrect() {
        excavation.addArchaeologist(ARCHAEOLOGIST);
        boolean result = excavation.removeArchaeologist(ARCHAEOLOGIST.getName());
        Assert.assertTrue(result);
    }
}
