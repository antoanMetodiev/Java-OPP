package p01_Database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {
    private Database database;
    private static final Integer[] EXPECTED_ELEMENTS = {5, 5, 5};
    private static final Integer EL_FOR_ADD = 10;

    @Before
    public void setup() throws OperationNotSupportedException {
        database = new Database(EXPECTED_ELEMENTS);
    }

    @Test
    public void test_Constructor_Should_Receive_Els_Correctly() throws OperationNotSupportedException {
        Integer[] currentEls = database.getElements();

        assertArrayEquals(EXPECTED_ELEMENTS, currentEls);
        assertEquals(EXPECTED_ELEMENTS.length, currentEls.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Elements_If_Not_Between_1_16_Should_Throw_Exception() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_AddMethod_When_Receive_Null_ShouldThrow_Exception() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_AddMethod_Should_AddEls_InTheEnd() throws OperationNotSupportedException {
        int previousLength = database.getElements().length;
        database.add(EL_FOR_ADD);

        // Check last el is Equal of added element -> EL_FOR_ADD.
        int lastIndex = database.getElements().length - 1;
        Integer lastEl = database.getElements()[lastIndex];
        assertEquals(EL_FOR_ADD, lastEl);

        int lengthAfterAddedEl = database.getElements().length;
        // Check previousLength variable is smaller than lengthAfterAddedEl variable.
        assertTrue(previousLength < lengthAfterAddedEl);
    }

    @Test
    public void test_RemoveMethod_Remove_Correctly() throws OperationNotSupportedException {
        int initialSize = database.getElements().length;
        database.remove();
        int sizeAfterRemoveMethod = database.getElements().length;

        assertEquals(initialSize - 1, sizeAfterRemoveMethod);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_RemoveMethod_ThrowException_When_DatabaseIsEmpty() throws OperationNotSupportedException {
        int repeated = database.getElements().length + 1;
        for (int i = 0; i < repeated; i++) {
            database.remove();
        }
    }


}
