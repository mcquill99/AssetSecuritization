package ASP;

import java.util.HashMap;

public class SPV {
    public HashMap<Integer, Loan> loan = new HashMap<>();

    public void buyLoan(double interestRate, Bank bank, SPV spv){
        bank.sellLoanToSPV(interestRate, spv);
    }
}
