import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class bankTest {

    @Test
    public void createLoanTest() throws IllegalArgumentException{
        Bank myBank = new Bank();

        assertThrows(IllegalArgumentException.class, () ->myBank.createLoan(-10,0), "Interest and balance must be positive");
        assertThrows(IllegalArgumentException.class, () ->myBank.createLoan(5,-100), "Interest and balance must be positive");

        Loan newLoan = myBank.createLoan(500, 5);
        assertEquals(.05, newLoan.getInterest());
        assertEquals(500, newLoan.getBalance());

        newLoan = myBank.createLoan(0, 100);
        assertEquals(1, newLoan.getInterest());
        assertEquals(0, newLoan.getBalance());

        newLoan = myBank.createLoan(100000, 25);
        assertEquals(.25, newLoan.getInterest());
        assertEquals(100000, newLoan.getBalance());

    }
}
