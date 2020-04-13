import ASP.AssetBackedSecurity;
import ASP.Loan;
import ASP.SPV;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        for(Loan loan: absToCheck.getLoanList()){
            if(loan.getInterest() < (min / 100) || loan.getInterest() > (max / 100)){
                return false;
            }
        }
        return true;
    }

    @Test
    public void createABSTest(){
        SPV spv;
        List<Loan> loans = generateLoanList(5,5,10);
        spv = new SPV(loans);

        //test for interest values outside of loan scope
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(90,100,100));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(15,20,100));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(25,50,100));

        //valid ABS creation test
        spv.createABS(5,10,1);
        assertTrue(checkABS(5,10,spv.createABS(5,10,1)));

        spv.createABS(5, 10, 4);
        assertTrue(checkABS(5,10,spv.createABS(5,10,1)));

        //test for no loans that arent already assigned to an abs
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,10,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,10,100));


        //test for maxInterest lower than minInterest
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(10,5,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(100,0,1));

        //test for negatives
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(-5,10,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(5,-10,1));
        assertThrows(IllegalArgumentException.class, () ->spv.createABS(-5,-10,1));


    }
}
