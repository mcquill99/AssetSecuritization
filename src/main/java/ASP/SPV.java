package ASP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SPV {
    private List<Loan> loans;
    private List<AssetBackedSecurity> ABSList;
    public HashMap<Integer, Loan> loan = new HashMap<>();
    private HashMap<Loan, Boolean> isInABS = new HashMap<>();
    private double balance;


    public SPV() {
        loans = new ArrayList<>();
        ABSList = new ArrayList<>();
    }

    public SPV(List<Loan> listOfLoans) {
        loans = listOfLoans;
        for (Loan loan : loans) {
            isInABS.put(loan, false);
        }
    }

    public void buyLoan(double interestRate, Bank bank, SPV spv) {
        bank.sellLoanToSPV(interestRate, spv);
    }

    public void addABStoList(double minInterest, double maxInterest, int numLoans){

    }

    public AssetBackedSecurity createABS(double minInterest, double maxInterest, int numLoans) throws insufficientLoansException, IllegalArgumentException {
        if (minInterest > maxInterest || minInterest < 0 || maxInterest < 0 || numLoans <= 0) {
            throw new IllegalArgumentException("Please enter all positive values, with minimum Interest being less than maximum interest, and the number of loans requested being higher than 0");
        }
        List<Loan> absLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getInterest() >= (minInterest / 100) && loan.getInterest() <= (maxInterest / 100) && !isInABS.get(loan) && absLoans.size() < numLoans) {
                absLoans.add(loan);
                isInABS.replace(loan, true);
            }
        }

        if (absLoans.size() == 0 || numLoans > absLoans.size()) {
            throw new insufficientLoansException("The number of loans you requested is not available, currently " + absLoans.size() + " loans are available in that range");
        }

        return new AssetBackedSecurity(absLoans);
    }

    public List<AssetBackedSecurity> getABSList() {
        return ABSList;
    }

    public void AssignRiskValue() {
    }
}


