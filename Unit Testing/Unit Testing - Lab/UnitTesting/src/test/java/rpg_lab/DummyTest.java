package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {
    private static final int ALIVE_DUMMY_INITIAL_HEALTH = 100;
    private static final int DEAD_DUMMY_HEALTH = 0;
    private static final int INITIAL_EXPERIENCE = 100;
    private static final int DAMAGE = 20;
    private Dummy aliveDummy;
    private Dummy deadDummy;


    @Before
    public void setup() {
        aliveDummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, INITIAL_EXPERIENCE);
        deadDummy = new Dummy(DEAD_DUMMY_HEALTH, INITIAL_EXPERIENCE);
    }

    @Test(expected = IllegalStateException.class)
    public void testDummyIsDeadShouldThrowException() {
        deadDummy = new Dummy(DEAD_DUMMY_HEALTH, INITIAL_EXPERIENCE);
        deadDummy.takeAttack(DAMAGE);
    }

    @Test(expected = IllegalStateException.class)
    public void testGiveExperienceMethodShouldThrowIllegalStateException() {
        aliveDummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, INITIAL_EXPERIENCE);
        aliveDummy.giveExperience();
    }

    @Test
    public void testDummyLosesHealth() {
        aliveDummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, INITIAL_EXPERIENCE);
        aliveDummy.takeAttack(DAMAGE);
        Assert.assertEquals(ALIVE_DUMMY_INITIAL_HEALTH - DAMAGE, aliveDummy.getHealth());
    }

    @Test
    public void testDummyGivesExperience() {
        deadDummy = new Dummy(DEAD_DUMMY_HEALTH, INITIAL_EXPERIENCE);
        Assert.assertEquals(INITIAL_EXPERIENCE, deadDummy.giveExperience());
    }
}