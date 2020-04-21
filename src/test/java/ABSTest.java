import ASP.AssetBackedSecurity;
import ASP.Loan;
import util.JsonUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
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
            double interest = loan.getInterest();
            toReturn = toReturn + interest;
        }

        return Math.round((toReturn / loans.size()) * 100000.0) / 100000.0;
    }

    @Test
    public void generateLoanListTest(){
        List<Loan> loans = generateLoanList(1, 5,5);
        assertEquals(.05, loans.get(0).getInterest());
        assertEquals(500, loans.get(0).getBalance());

        loans = generateLoanList(10, 10,10);
        for(int i = 0; i < 10; i++){
            assertEquals(0.1, loans.get(i).getInterest());
            assertEquals(500, loans.get(0).getBalance());
        }
    }

    @Test
    public void getAverageInterestTest(){
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(500, 5));
        assertEquals(.05, getAverageInterest(loans));

        loans.add(new Loan(500, 10));
        assertEquals(.075, getAverageInterest(loans));

        loans.add(new Loan(500, 7.5));
        assertEquals(.075, getAverageInterest(loans));

        loans.add(new Loan(500, 5));
        assertEquals(.06875, getAverageInterest(loans));

    }


    @Test
    public void generateRiskValueTest() throws IOException {

        AssetBackedSecurity abs;
        List<Loan> loans;
        for(int i = 0; i < 100; i++){
            loans = generateLoanList(i * 10, 10, 10);
            abs = new AssetBackedSecurity(loans);
            abs.generateRiskValue();

            assertEquals(getAverageInterest(loans), abs.getRiskValue());

        }

        abs = JsonUtil.fromJsonFile("src/test/resources/AssetBackedSecurityTest.json", AssetBackedSecurity.class);

        abs.generateRiskValue();
        assertEquals(7, abs.getRiskValue());

    }

    @Test
    public void getRiskValueTest() throws IOException {
        AssetBackedSecurity abs;
        List<Loan> loans;
        for(int i = 0; i < 100; i++){
            loans = generateLoanList(i * 10, 5, 10);
            abs = new AssetBackedSecurity(loans);
            abs.generateRiskValue();

            assertEquals(getAverageInterest(loans), abs.getRiskValue());
        }
        abs = JsonUtil.fromJsonFile("src/test/resources/AssetBackedSecurityTest.json", AssetBackedSecurity.class);
        abs.generateRiskValue();
        assertEquals(7, abs.getRiskValue());


    }

    @Test
    public void numberOfLoansTest() throws IOException {

        AssetBackedSecurity abs = JsonUtil.fromJsonFile("src/test/resources/AssetBackedSecurityTest.json", AssetBackedSecurity.class);
        assertEquals(5, abs.numberOfLoans());
    }
}
