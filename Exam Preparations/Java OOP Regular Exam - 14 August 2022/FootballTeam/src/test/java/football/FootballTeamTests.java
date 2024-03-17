package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {
    private FootballTeam footballTeam;
    private static final String EXPECTED_NAME = "Lilyan";
    private static final int EXPECTED_VACANT_POINTS = 5;

    @Before
    public void setup() {
        footballTeam = new FootballTeam(EXPECTED_NAME, EXPECTED_VACANT_POINTS);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_IfReceiveNull_ShouldThrow() {
        footballTeam = new FootballTeam(null, EXPECTED_VACANT_POINTS);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_IfReceiveEmptySpaces_ShouldThrow() {
        footballTeam = new FootballTeam("  ", EXPECTED_VACANT_POINTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVacantPositions_IfReceiveInvalidNumber_ShouldThrow() {
        footballTeam = new FootballTeam(EXPECTED_NAME, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballer_IfSizeIsBiggerOfVacantP_ShouldThrow() {
        for (int i = 0; i < EXPECTED_VACANT_POINTS + 1; i++) {
            footballTeam.addFootballer(new Footballer("Random Name"));
        }
    }

    @Test
    public void testAddFootballer_ShouldWorkCorrect() {
        int initialSize = footballTeam.getCount();
        footballTeam.addFootballer(new Footballer("Random Name"));
        Assert.assertEquals(initialSize + 1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballer_IfReceiveInvalidName_ShouldThrow() {
        footballTeam.removeFootballer("Invalid Name");
    }

    @Test
    public void testRemoveFootballer_ShouldWorkCorrect() {
        footballTeam.addFootballer(new Footballer("Random Name"));
        int initialSize = footballTeam.getCount();
        footballTeam.removeFootballer("Random Name");
        Assert.assertEquals(initialSize - 1, footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSale_IfReceiveInvalidName_ShouldThrow() {
        footballTeam.footballerForSale("Invalid Name");
    }

    @Test
    public void testFootballerForSale_ShouldWorkCorrect() {
        footballTeam.addFootballer(new Footballer("Random Name"));
        Footballer receivedPlayer = footballTeam.footballerForSale("Random Name");
        Assert.assertFalse(receivedPlayer.isActive());
        Assert.assertEquals(receivedPlayer.getName(), "Random Name");
    }

    @Test
    public void testGetStatistics_ShouldWorkCorrect() {
        footballTeam.addFootballer(new Footballer("Pesho"));
        String EXPECTED_RESULT = String.format("The footballer Pesho is in the team %s.", footballTeam.getName());
        Assert.assertEquals(EXPECTED_RESULT, footballTeam.getStatistics());
    }

    @Test
    public void testConstructor_ShouldWorkCorrect() {
        Assert.assertEquals(footballTeam.getVacantPositions(), EXPECTED_VACANT_POINTS);
        Assert.assertEquals(footballTeam.getName(), EXPECTED_NAME);
    }
}
