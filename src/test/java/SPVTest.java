import ASP.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import ASP.AssetBackedSecurity;
import ASP.Loan;
import ASP.SPV;
import ASP.insufficientLoansException;

import static org.junit.jupiter.api.Assertions.*;

public class SPVTest {

    public List<Loan> generateLoanList(int numOfLoans, int minInterest, int maxInterest){
        List<Loan> toReturn = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < numOfLoans; i++){
            toReturn.add(new Loan(500, r.nextInt((maxInterest - minInterest)+ 1) + minInterest));
        }

        return toReturn;
    }

    public boolean checkABS(double min, double max, AssetBackedSecurity absToCheck){
        for(Loan loan: absToCheck.getLoans()){
            if(loan.getInterest() < (min / 100) || loan.getInterest() > (max / 100)){
                return false;
            }
        }
        return true;
    }

    @Test
    public void buyLoanTest(){
        SPV testSpv = new SPV();
        Bank testBank = new Bank(30000);
        testBank.createLoan(2500,10); // id: 1
        testBank.createLoan(3000,20); // id: 2

        testSpv.buyLoan(10,testBank,testSpv);
        testSpv.buyLoan(20,testBank,testSpv);
        //testSpv.buyLoan(50,testBank,testSpv);

    }

    @Test
    public void createABSTest() throws insufficientLoansException {
        SPV spv;
        List<Loan> loans = generateLoanList(5,5,10);
        spv = new SPV(loans);

        //test for interest values outside of loan scope
        assertThrows(insufficientLoansException.class, () ->spv.createABS(90,100,100));
        assertThrows(insufficientLoansException.class, () ->spv.createABS(15,20,100));
        assertThrows(insufficientLoansException.class, () ->spv.createABS(25,50,100));

        //valid ABS creation test
        assertTrue(checkABS(5,10,spv.createABS(5,10,1)));

        assertTrue(checkABS(5,10,spv.createABS(5,10,4)));

        //test for no loans that arent already assigned to an abs
        assertThrows(insufficientLoansException.class, () ->spv.createABS(5,10,1));
        assertThrows(insufficientLoansException.class, () ->spv.createABS(5,10,100));


        //test for maxInterest lower than minInterest
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(10,5,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(100,0,1));

        //test for negatives
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(-5,10,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,-10,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(-5,-10,1));

        //test for negative numLoans request
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,10,0));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,10,-5));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,10,-100));

    }

    public double getAverageRisk(List<AssetBackedSecurity> absList){
        double toReturn = 0;
        for (AssetBackedSecurity abs :absList) {
            abs.generateRiskValue();
            toReturn += abs.getRiskValue();
        }

        return toReturn / absList.size();
    }

    @Test
    public void assignRiskValueTest() throws insufficientLoansException {
        SPV spv;
        List<Loan> loans = generateLoanList(1000,5,15);
        spv = new SPV(loans);

        spv.createABS(5,10,3);
        spv.createABS(7,15,2);


        spv.AssignRiskValue();

        List<Double> list1 = spv.AssignRiskValue();

        Double d;
        assertEquals(list1.get(0), d = spv.getABSList().get(0).getRiskValue());

    }

}



