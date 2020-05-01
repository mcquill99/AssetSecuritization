import ASP.AssetBackedSecurity;
import ASP.Bank;
import ASP.Loan;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import util.JsonUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    public void Loans() throws IOException {
        Loan loan = new Loan(500,5);
        JsonUtil.toJsonFile("src/test/resources/loanTest.json", loan);
        Loan loanJSON = JsonUtil.fromJsonFile("src/test/resources/loanTest.json", Loan.class);

        assertEquals(loan.getBalance(), loanJSON.getBalance());
        assertEquals(loan.getInterest(), loanJSON.getInterest());
    }

    @Test
    public void ABS() throws IOException {
        List<Loan> loanList = generateLoanList(5, 5, 10);
        for(Loan loan : loanList){
            System.out.println(loan.getInterest());
        }
        AssetBackedSecurity abs = new AssetBackedSecurity(loanList);
        JsonUtil.toJsonFile("src/test/resources/absTest.json", abs);
        AssetBackedSecurity absJSON = JsonUtil.fromJsonFile("src/test/resources/absTest.json", AssetBackedSecurity.class);
        assertEquals(abs.getRiskValue(), absJSON.getRiskValue());
    }
    @Test
    public void Bank() throws IOException {
        Bank bank = new Bank(10000);
        bank.createLoan(1000, 5);
        bank.createLoan(1000,5);
        bank.createLoan(500, 10);
        bank.createLoan(500, 10);
        bank.createLoan(500, 10);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(bank);
        System.out.println(json);
        //JsonUtil.toJsonFile("src/test/resources/bankTest.json", bank);
        //Bank bank2 = JsonUtil.fromJsonFile("src/test/resources/bankTest.json", Bank.class);
        Bank bank2 = mapper.readValue(json, Bank.class);

        assertEquals(bank.getLoans().get(1).getValue().getBalance(), bank2.getLoans().get(1).getValue().getBalance());
        assertEquals(bank.getLoans().get(1).getValue().getInterest(), bank2.getLoans().get(1).getValue().getInterest());

    }

    @Test
    public void SPVTest(){
        
    }
}
