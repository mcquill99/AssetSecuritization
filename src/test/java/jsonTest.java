import ASP.*;
import IO.BankWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public double getAverageInterest(List<Loan> loans){
        double toReturn = 0;
        for (Loan loan : loans) {
            double interest = loan.getInterest();
            toReturn = toReturn + interest;
        }

        return Math.round((toReturn / loans.size()) * 100000.0) / 100000.0;
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
    public void ASPTest() throws IOException {
        List<Loan> loans = generateLoanList(5,5,10);
        AssetBackedSecurity asp = new AssetBackedSecurity(loans);
        JsonUtil.toJsonFile("src/test/resources/absTest.json", asp);
        AssetBackedSecurity asp2 = JsonUtil.fromJsonFile("src/test/resources/absTest.json", AssetBackedSecurity.class);
        assertEquals(asp.getLoanList().get(0).getBalance(), asp2.getLoanList().get(0).getBalance());
        assertEquals(asp.getRiskValue(), asp2.getRiskValue());

    }
    @Test
    public void bankTest() throws IOException {
        Bank bank = new Bank(1000);
        bank.createLoan(1000,5);
        bank.createLoan(500, 5);
        bank.createLoan(1500, 10);
        bank.createLoan(2000, 10);
        bank.createLoan(5000, 20);

        BankWriter bankWriter = new BankWriter(bank);
        JsonUtil.toJsonFile("src/test/resources/bankTest.json", bankWriter);
        //ObjectMapper mapper = new ObjectMapper();
        //String json = mapper.writeValueAsString(bankWriter);
        //System.out.println(json);
        //BankWriter writer2 = mapper.readValue(json, BankWriter.class);
        BankWriter bankWriter2 = JsonUtil.fromJsonFile("src/test/resources/bankTest.json", BankWriter.class);
        Bank bank2 = bankWriter2.CreateBank();
        assertEquals(bank.getBalance(), bank2.getBalance());


    }
    @Test
    public void spvTest(){

    }

}
