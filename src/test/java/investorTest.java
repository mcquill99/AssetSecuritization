import ASP.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class investorTest {

    public List<Loan> generateLoanList(int numOfLoans, int minInterest, int maxInterest){
        List<Loan> toReturn = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < numOfLoans; i++){
            toReturn.add(new Loan(500, r.nextInt((maxInterest - minInterest)+ 1) + minInterest));
        }

        return toReturn;
    }

//    @Test
//    public void balanceTest(){
//        List<AssetBackedSecurity> ABSinvestedIn[] = new ABS[0];
//        Investor Prav = new Investor(30000, ABSinvestedIn);
//        assertEquals(30000, Prav.getBalance());
//
//    }

    List<Loan> loans = generateLoanList(85,5,15);


    @Test
    public void listABSTest() throws insufficientLoansException {

        List<AssetBackedSecurity> portfolio = new ArrayList<>();
        Investor prav = new Investor(20, portfolio);

        SPV locker = new SPV(loans);
        locker.createABS(5,10,12);
        locker.createABS(7,12,15);
        locker.createABS(10,15,10);

        prav.listABS(locker,5,15);


    }

}
