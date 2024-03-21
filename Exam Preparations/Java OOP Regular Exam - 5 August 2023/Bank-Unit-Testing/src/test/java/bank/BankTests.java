package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTests {
    private Bank bank;
    private static final String EXPECTED_NAME = "Lilyan";
    private static final int EXPECTED_CAPACITY = 5;

    @Before
    public void setup() {
        bank = new Bank(EXPECTED_NAME, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_IfReceiveNull_Throw() {
        bank = new Bank(null, EXPECTED_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_IfReceiveEmptySpaces_Throw() {
        bank = new Bank("   ", EXPECTED_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacity_IfReceiveInvalidNum() {
        bank = new Bank(EXPECTED_NAME, -1);
    }

    @Test
    public void testSetCapacity_ShouldWorkCorrect() {
        Assert.assertEquals(bank.getCapacity(), EXPECTED_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClient_IfSizeIsEuqalOfCapacity_Throw() {
        for (int i = 0; i < EXPECTED_CAPACITY + 1; i++) {
            bank.addClient(new Client(EXPECTED_NAME));
        }
    }

    @Test
    public void testAddClient_ShouldWorkCorrect() {
        int initialSize = bank.getCount();
        bank.addClient(new Client("Random Name"));
        int sizeAfterAddedEl = bank.getCount();

        Assert.assertEquals(initialSize + 1, sizeAfterAddedEl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveClient_IfReceiveInvalidClient() {
        bank.removeClient("Invalid Name");
    }

    @Test
    public void testRemoveClient_ShouldWorkCorrect() {
        bank.addClient(new Client("Random Name"));

        int initialSize = bank.getCount();
        bank.removeClient("Random Name");
        int sizeBeforeRemovedEl = bank.getCount();
        Assert.assertEquals(initialSize - 1, sizeBeforeRemovedEl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawal_IfReceiveInvalidName() {
        bank.loanWithdrawal("Invalid Name");
    }

    @Test
    public void testLoanWithdrawal_ShouldWorkCorrect() {
        Client client = new Client("Larry");
        bank.addClient(client);

        Client receivedClient = bank.loanWithdrawal("Larry");
        Assert.assertFalse(receivedClient.isApprovedForLoan());
        Assert.assertEquals(client.getName(), receivedClient.getName());
    }

    @Test
    public void testStatistics_ShouldWorkCorrect() {
        bank.addClient(new Client("Larry"));
        String EXPECTED_TEXT = String.format("The client Larry is at the %s bank!", bank.getName());
        Assert.assertEquals(EXPECTED_TEXT, bank.statistics());
    }
}
