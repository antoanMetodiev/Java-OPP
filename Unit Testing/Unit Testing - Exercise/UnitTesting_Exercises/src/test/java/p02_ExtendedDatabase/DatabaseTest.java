package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {
    private Database database;
    private static final Person[] EXPECTED_ELEMENTS = {new Person(111, "Antoan"), new Person(111, "Lilyan")};
    private static final Person PERSON_FOR_ADD = new Person(111, "Rumen");
    private static final String USERNAME_FOR_FIND = "Rumen";
    private static final Person EXPECTED_PERSON = new Person(111, "Rumen");


    @Before
    public void setup() throws OperationNotSupportedException {
        database = new Database(EXPECTED_ELEMENTS);
    }

    //// Test SetElements Method:
    @Test(expected = OperationNotSupportedException.class)
    public void testSetElsM_If_Are_Not_Receive_Between_1_16_Els_ShouldThrowException() throws OperationNotSupportedException {
        database = new Database();
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //// Tests for Add-Method:
    @Test(expected = OperationNotSupportedException.class)
    public void test_AddMethod_If_Receive_Null_ShouldThrowEx() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_AddMethod_If_Receive_Els() throws OperationNotSupportedException {
        int previousSize = database.getElements().length;
        database.add(PERSON_FOR_ADD);

        int lastDatabaseIndex = database.getElements().length - 1;
        Person lastAddedPerson = database.getElements()[lastDatabaseIndex];

        Assert.assertEquals(lastAddedPerson.getUsername(), PERSON_FOR_ADD.getUsername());
        Assert.assertEquals(lastAddedPerson.getId(), PERSON_FOR_ADD.getId());

        int sizeAfterAddedEl = database.getElements().length;
        Assert.assertEquals(previousSize + 1, sizeAfterAddedEl);
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // Tests for Remove Method:
    @Test(expected = OperationNotSupportedException.class)
    public void test_RemoveMethod_If_Remove_With_ZeroSize_ShouldThrowException() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    // Tests for FindByUsername Method:
    @Test(expected = OperationNotSupportedException.class)
    public void test_findByUsernameM_When_Receive_Null() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void test_findByUsernameMShouldWorkCorrect() throws OperationNotSupportedException {
        database = new Database(EXPECTED_PERSON);
        Assert.assertEquals(database.findByUsername(USERNAME_FOR_FIND).getUsername(), EXPECTED_PERSON.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_findByUsernameM_If_FoundEls_MoreOrLessThan_1() throws OperationNotSupportedException {
        // if you have more than 1!
        database.add(EXPECTED_PERSON);
        database.add(EXPECTED_PERSON);
        database.findByUsername(USERNAME_FOR_FIND);

        // if you don't have any of this el
        database.findByUsername("(Random Name)");
    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}