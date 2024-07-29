import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class BankAccountTest {
    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("12345", 100.00, "John Doe", "email", "1234567890");
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    // add parameterized test for deposit here
    @Test
    void testWithdraw() {
        BankAccount account = new
        BankAccount("12345", 100.00, "John Doe", "email", "1234567890");
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    // add parameterized test for getBalance using a CSV source here
    /**
     * Test case for the getBalance() method of the BankAccount class.
     * It verifies that the getBalance() method returns the expected balance.
     *
     * @param balance   the initial balance of the bank account
     * @param expected  the expected balance after calling the getBalance() method
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/getBalance.csv")
    void testGetBalance(double balance, double expected) {
        BankAccount account = new BankAccount("12345", balance, "John Doe", "email", "1234567890");
        assertEquals(expected, account.getBalance());
    }

}
