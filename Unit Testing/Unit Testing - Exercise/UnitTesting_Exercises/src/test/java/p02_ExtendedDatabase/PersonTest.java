package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {
    private static final int EXPECTED_ID = 111;
    private static final String EXPECTED_USERNAME = "Lilyan";

    @Test
    public void testConstructorShouldWorkCorrect() {
        Person person = new Person(EXPECTED_ID, EXPECTED_USERNAME);
        Assert.assertEquals(person.getId(), EXPECTED_ID);
        Assert.assertEquals(person.getUsername(), EXPECTED_USERNAME);
    }
}