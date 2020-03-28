import ASP.Bank;
import ASP.Borrower;
import ASP.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class borrowerTest {
    @Test
    public void constructorTest(){
        assertThrows(IllegalArgumentException.class, () ->new Borrower(-300), "Starting Balance may not be less than 0");
        Borrower test = new Borrower(25.67);
        assertEquals(25.67,test.balance);
    }

    @Test
    public void checkBalanceTest(){
        Borrower test = new Borrower(2785.67);
        assertEquals(2785.67,test.checkBalance());
    }

    @Test
    public void buyLoanTest(){
        //creates a borrower and a bank
        Borrower test = new Borrower(50.56);
        Bank chase = new Bank(700);

        //calls the buy loan function
        test.buyLoan(100,chase);

        //makes sure the borrrower has the right balances after the loan is bought
        assertEquals(150.56,test.checkBalance());
        assertEquals(100,test.loanBalance);

        //makes sure the bank has the right information about the borrower
        assertEquals(150.56,chase.customers.get(1).balance);
        assertEquals(100,chase.customers.get(1).loanBalance);

        //makes sure the bank contains the loan in the collection
        assert(chase.loans.containsKey(1));

        //attempts to buy a loan that is too large.
        assertThrows(IllegalArgumentException.class, () -> test.buyLoan(1000,chase));

    }

}
