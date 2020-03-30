import ASP.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class loanTest {

    @Test
    public void constructorTest(){
        assertThrows(IllegalArgumentException.class, () ->new Loan(-10,0), "Interest and balance must be positive");
        assertThrows(IllegalArgumentException.class, () ->new Loan(5,-100), "Interest and balance must be positive");
        assertThrows(IllegalArgumentException.class, () ->new Loan(5.00123,-100), "Interest and balance must be positive");
        assertThrows(IllegalArgumentException.class, () ->new Loan(5.00123,-100.5468786456465456), "Interest and balance must be positive");
        assertThrows(IllegalArgumentException.class, () ->new Loan(5.0,-100.515133153), "Interest and balance must be positive");
        assertThrows(IllegalArgumentException.class, () ->new Loan(0,-100.515133153), "Interest and balance must be positive");
    }

    @Test
    public void getBalanceTest(){
        Loan myLoan = new Loan(500, 5);
        assertEquals(500, myLoan.getBalance());
        myLoan.accrueInterest();
        assertEquals(525, myLoan.getBalance());
        myLoan.subtractFromTotal(525);
        assertEquals(0, myLoan.getBalance());


    }

    @Test
    public void getInterestTest(){
        Loan myLoan = new Loan(500, 5);
        assertEquals(.05,  myLoan.getInterest());

        myLoan = new Loan(500, 50);
        assertEquals(.5,  myLoan.getInterest());

        myLoan = new Loan(500, 100);
        assertEquals(1,  myLoan.getInterest());



    }

    @Test
    public void accrueInterestTest(){
        Loan myLoan = new Loan(500, 1);
        myLoan.accrueInterest();
        assertEquals(505, myLoan.getBalance());

        myLoan.accrueInterest();
        assertEquals(510.05, myLoan.getBalance());

        myLoan.accrueInterest();
        assertEquals(515.15, myLoan.getBalance());
    }

    @Test
    public void subtractFromTotalTest(){
        Loan myLoan = new Loan(500, 1);
        myLoan.subtractFromTotal(1);
        assertEquals(499, myLoan.getBalance());

        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(1.349), "Amount must be less than or equal to balance and must have at most 2 decimal places");
        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(459.3499580948309854), "Amount must be less than or equal to balance and must have at most 2 decimal places");
        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(1.9999999), "Amount must be less than or equal to balance and must have at most 2 decimal places");


        myLoan.subtractFromTotal(99);
        assertEquals(400, myLoan.getBalance());

        myLoan.subtractFromTotal(400);
        assertEquals(0, myLoan.getBalance());

        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(1), "Amount must be less than or equal to balance and must have at most 2 decimal places");
        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(100), "Amount must be less than or equal to balance and must have at most 2 decimal places");
        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(-1), "Amount must be less than or equal to balance and must have at most 2 decimal places");
        assertThrows(IllegalArgumentException.class, () ->myLoan.subtractFromTotal(-10000), "Amount must be less than or equal to balance and must have at most 2 decimal places");

        //Add tests for multiple decimals

    }

}
