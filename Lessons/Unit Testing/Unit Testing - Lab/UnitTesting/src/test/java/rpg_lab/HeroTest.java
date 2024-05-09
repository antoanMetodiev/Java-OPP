package rpg_lab;

import org.junit.Assert;
import org.junit.Test;

public class HeroTest {

    @Test
    public void testHeroReceiveXpAfterDummyIsDead() {
        Dummy dummy = new Dummy(0, 5);
        Hero hero = new Hero("Antoan");

        int previousHp = hero.getExperience();

        hero.attack(dummy);
        Assert.assertTrue(previousHp < hero.getExperience());
    }


}