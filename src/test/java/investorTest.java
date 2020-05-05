import ASP.*;
import org.junit.jupiter.api.Test;
import util.JsonUtil;

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

    @Test
    public void balanceTest(){
        List<AssetBackedSecurity> ABSinvestedIn = new ArrayList<>();
        Investor Prav = new Investor(30000, ABSinvestedIn);
        assertEquals(30000, Prav.getBalance());

        Investor sean = new Investor(24.87, ABSinvestedIn);
        assertEquals(24.87, sean.getBalance());


    }

    List<Loan> loans = generateLoanList(85,5,15);


    @Test
    public void listABSTest() throws insufficientLoansException {

        List<AssetBackedSecurity> portfolio = new ArrayList<>();
        Investor prav = new Investor(20, portfolio);

        SPV locker = new SPV(loans);
        assertThrows(IllegalArgumentException.class, () -> prav.listABS(locker,5,15)); // tests for an spv with no ABS

        locker.createABS(5,10,12);
        System.out.println(prav.listABS(locker,5,15));

        locker.createABS(7,12,15);
        locker.createABS(10,15,10);

        System.out.println(prav.listABS(locker,5,15));

    }



    @Test
    public void buyABSTest() throws insufficientLoansException, insufficientSharesException {
        Investor investor = new Investor(100, new ArrayList<>());
        investor.setId(1);
        List<Loan> loanList = generateLoanList(10, 5, 5);
        SPV spv = new SPV(loanList);
        spv.createABS(5,5,5);
        investor.buyABS(1, spv, 15);
        assertEquals(.05, investor.getABSinvestedIn().get(0).getRiskValue());
        assertEquals(5, investor.getABSinvestedIn().get(0).getLoans().size());
    }

    @Test
    public void viewMyABSTest() throws insufficientLoansException, insufficientSharesException {
        Investor investor = new Investor(100, new ArrayList<>());
        List<Loan> loanList = generateLoanList(50, 5, 15);
        SPV spv = new SPV(loanList);
        spv.createABS(5,5,5);
        spv.createABS(10,15,8);

        System.out.println(investor.viewMyABS()); //should return an empty string exception


        investor.buyABS(1, spv, 12);
        investor.buyABS(2, spv, 15);

        System.out.println(investor.viewMyABS());


    }

}
