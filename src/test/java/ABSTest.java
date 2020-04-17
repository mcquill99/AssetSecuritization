import ASP.AssetBackedSecurity;
import ASP.Loan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ABSTest {

    public List<Loan> generateLoanList(int numOfLoans, int minInterest, int maxInterest){
        List<Loan> toReturn = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < numOfLoans; i++){
            toReturn.add(new Loan(500, r.nextInt((maxInterest - minInterest)+ 1) + minInterest));
        }

        return toReturn;
    }

    public double getAverageInterest(List<Loan> loans){
        double toReturn = 0;
        for (Loan loan : loans) {
            toReturn += loan.getInterest();
        }

        return toReturn / loans.size();
    }

    @Test
    public void generateRiskValueTest(){

        AssetBackedSecurity abs;
        List<Loan> loans;
        for(int i = 0; i < 100; i++){
            loans = generateLoanList(i * 10, 5, 10);
            abs = new AssetBackedSecurity(loans);
            abs.generateRiskValue();

            assertEquals(getAverageInterest(loans), abs.getRiskValue());
        }



    }

    @Test
    public void getRiskValueTest(){
        AssetBackedSecurity abs;
        List<Loan> loans;
        for(int i = 0; i < 100; i++){
            loans = generateLoanList(i * 10, 5, 10);
            abs = new AssetBackedSecurity(loans);
            abs.generateRiskValue();

            assertEquals(getAverageInterest(loans), abs.getRiskValue());
        }
    }
}
