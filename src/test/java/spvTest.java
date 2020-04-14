import org.junit.jupiter.api.Test;
import ASP.Bank;
import ASP.SPV;
import ASP.Loan;

public class spvTest {

    @Test
    public void buyLoanTest(){
        SPV testSpv = new SPV();
        Bank testBank = new Bank(30000);
        testBank.createLoan(2500,10); // id: 1
        testBank.createLoan(3000,20); // id: 2

        testSpv.buyLoan(10,testBank,testSpv);
        testSpv.buyLoan(20,testBank,testSpv);
        testSpv.buyLoan(50,testBank,testSpv);

    }
}
