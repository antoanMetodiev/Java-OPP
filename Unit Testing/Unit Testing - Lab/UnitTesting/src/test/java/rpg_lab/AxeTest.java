package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class AxeTest {
    private static final int ATTACK_DAMAGE = 50;
    private static final int AXE_DURABILITY = 30;
    private static final int BROKEN_AXE_DURABILITY = 0;
    private static final int ALIVE_DUMMY_INITIAL_HEALTH = 100;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final int AXE_DURABILITY_LOSS = 1;
    private Dummy dummy;
    private Axe axe;

    @Before
    public void setup() {
        axe = new Axe(ATTACK_DAMAGE, AXE_DURABILITY);
        dummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testAxeDurabilityLosesPointsAfterHit() {
        axe = new Axe(ATTACK_DAMAGE, AXE_DURABILITY);
        dummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);

        axe.attack(dummy);
        Assert.assertEquals(AXE_DURABILITY - AXE_DURABILITY_LOSS, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackMethodShouldThrowIllegalStateException() {
        axe = new Axe(ATTACK_DAMAGE, BROKEN_AXE_DURABILITY);
        dummy = new Dummy(ALIVE_DUMMY_INITIAL_HEALTH, DUMMY_EXPERIENCE);

        axe.attack(dummy);
    }
}