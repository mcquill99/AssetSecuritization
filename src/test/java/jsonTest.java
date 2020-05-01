import ASP.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import ASP.AssetBackedSecurity;
import ASP.Loan;
import ASP.SPV;
import ASP.insufficientLoansException;
import util.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;

public class jsonTest {
    public List<Loan> generateLoanList(int numOfLoans, int minInterest, int maxInterest){
        List<Loan> toReturn = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < numOfLoans; i++){
            toReturn.add(new Loan(500, r.nextInt((maxInterest - minInterest)+ 1) + minInterest));
        }

        return toReturn;
    }

    @Test
    public void loanTest() throws IOException {
        Loan loan = new Loan(500,5);
        JsonUtil.toJsonFile("src/test/resources/loanTest.json", loan);
        Loan loan2 = JsonUtil.fromJsonFile("src/test/resources/loanTest.json", Loan.class);

        assertEquals(loan.getInterest(), loan2.getInterest());
        assertEquals(loan.getBalance(), loan2.getBalance());

    }
    @Test
    public void ASPTest(){

    }
    @Test
    public void bankTest(){

    }
    @Test
    public void spvTest(){

    }

}
