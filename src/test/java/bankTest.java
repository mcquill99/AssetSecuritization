import ASP.Bank;
import ASP.Borrower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class bankTest {

    @Test
    public void createLoanTest(){
        Bank testBank = new Bank(30000);
        testBank.createLoan(2500,10);
        testBank.createLoan(3000,20);

        assertEquals(2500, testBank.loans.get(1).getBalance());
        assertEquals(0.1,testBank.loans.get(1).getInterest());

        assertEquals(3000, testBank.loans.get(2).getBalance());
        assertEquals(0.2,testBank.loans.get(2).getInterest());

        assertThrows(IllegalArgumentException.class, () -> testBank.createLoan(-3,-7),"Invalid balance or interest on Loan");

    }

    @Test
    public void checkBalanceTest(){
        Bank testBank = new Bank(200);
        assertEquals(200,testBank.getBalance());
        Bank testBank2 = new Bank(3500.69);
        assertEquals(3500.69,testBank2.getBalance());
    }

    @Test
    public void releaseAssetTest(){
        Bank testBank = new Bank(300);
        //create two loans
        testBank.createLoan(2500,10);
        testBank.createLoan(3000,20);

        //release loanID 2 then make sure its null
        testBank.releaseAsset(2);
        assertNull(testBank.loans.get(2));

        //try to release an asset that does not exist(invalid loanID)
        assertThrows(IllegalArgumentException.class, () -> testBank.releaseAsset(60));

    }

    @Test
    public void sellLoanTest() {
        Bank chase = new Bank(40000);
        Borrower joel = new Borrower(60);
        Borrower prav = new Borrower(2.50);

        //makes sure joel has $60 before buying the loan
        assertEquals(60, joel.checkBalance());

        chase.sellLoan(200, joel);
        //makes sure joel has $260 after taking out the loan
        assertEquals(260, chase.customers.get(1).checkBalance());

        //makes sure the loan is in the system
        assertTrue(chase.loans.containsKey(1));
        assertEquals(200,chase.loans.get(1).getBalance());
    }



}
