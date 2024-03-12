package handball;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

public class TeamTests {
    private Team team;
    private static final String EXPECTED_NAME = "Lilyan";
    private static final int EXPECTED_POSITION = 5;
    private static final String INVALID_NAME = "KOBI";
    private static final int INVALID_POSITION = -1;

    @Before
    public void setup() {
        team = new Team(EXPECTED_NAME, EXPECTED_POSITION);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_IfReceiveNull_ShouldThrow() {
        team = new Team(null, EXPECTED_POSITION);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_IfReceiveEmptySpase_ShouldThrow() {
        team = new Team("  ", EXPECTED_POSITION);
    }

    @Test
    public void testSetName_ShouldWorkCorrect() {
        Assert.assertEquals(team.getName(), EXPECTED_NAME);
    }
  
    @Test(expected = IllegalArgumentException.class)
    public void testSetPosition_IfReceiveInvalidNumber_ShouldThrow() {
        team = new Team(EXPECTED_NAME, INVALID_POSITION);
    }

    @Test
    public void testSetPosition_ShouldWorkCorrect() {
        Assert.assertEquals(EXPECTED_POSITION, team.getPosition());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddM_IfSizeIsBiggerThanPosition_ShouldThrow() {
        for (int i = 0; i < EXPECTED_POSITION + 1; i++) {
            team.add(new HandballPlayer("Ronny"));
        }
    }

    @Test
    public void testAddM_ShouldWorkCorrect() {
        int EXPECTED_SIZE = team.getCount() + 1;
        team.add(new HandballPlayer("Ronny"));
        Assert.assertEquals(team.getCount(), EXPECTED_SIZE);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveM_IfReceiveInvalidName() {
        team.remove(INVALID_NAME);
    }

    @Test
    public void testRemoveM_ShouldWorkCorrect() {
        team.add(new HandballPlayer("Nurmagomedov"));
        int initialSize = team.getCount();
        team.remove("Nurmagomedov");
        Assert.assertEquals(initialSize - 1, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerForAnotherTeamM_IfReceiveInvalidName() {
        team.playerForAnotherTeam(INVALID_NAME);
    }

    @Test
    public void testPlayerForAnotherTeamM_ShouldWorkCorrect() {
        String EXPECTED_NAME = "Stanimir Etov";
        team.add(new HandballPlayer("Stanimir Etov"));
        HandballPlayer receivedName = team.playerForAnotherTeam("Stanimir Etov");

        Assert.assertEquals(receivedName.getName(), EXPECTED_NAME);
        Assert.assertFalse(receivedName.isActive());
    }
    
    @Test
    public void testGetStatisticsM_ShouldWorkCorrect() {
        team.add(new HandballPlayer("Puh"));
        String EXPECTED_RESULT = String.format("The player %s is in the team %s.", "Puh", team.getName());
        Assert.assertEquals(EXPECTED_RESULT, team.getStatistics());
    }
}
