package ASP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SPV {
    private List<Loan> loans;
    public List<AssetBackedSecurity> ABSList = new ArrayList<>();
    //public HashMap<Loan, Boolean> isInABS = new HashMap<>();
    private double balance = 0;
    public double SPVriskAverage;
    private int id = 0;


    public SPV() {
        loans = new ArrayList<>();
        ABSList = new ArrayList<>();
    }


    public SPV(List<Loan> listOfLoans) {
        loans = listOfLoans;
        for (Loan loan : loans) {
            loan.setSPVId(id);
        }
    }

    public void buyLoan(double interestRate, Bank bank, SPV spv) {
        bank.sellLoanToSPV(interestRate, spv);
    }


    public AssetBackedSecurity createABS(double minInterest, double maxInterest, int numLoans) throws insufficientLoansException, IllegalArgumentException {
        if (minInterest > maxInterest || minInterest < 0 || maxInterest < 0 || numLoans <= 0) {
            throw new IllegalArgumentException("Please enter all positive values, with minimum Interest being less than maximum interest, and the number of loans requested being higher than 0");
        }
        List<Loan> absLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getInterest() >= (minInterest / 100) && loan.getInterest() <= (maxInterest / 100) && !loan.getIsInAbs() && absLoans.size() < numLoans) {
                absLoans.add(loan);
                loan.setIsInAbs(true);
            }
        }

        if (absLoans.size() == 0 || numLoans > absLoans.size()) {
            throw new insufficientLoansException("The number of loans you requested is not available, currently " + absLoans.size() + " loans are available in that range");
        }

        AssetBackedSecurity absToAdd = new AssetBackedSecurity(absLoans);
        ABSList.add(absToAdd);

        return absToAdd;
    }

    public List<AssetBackedSecurity> getABSList() {
        return ABSList;
    }

    public List<Double> AssignRiskValue() {
        List<Double> ABSrisk = new ArrayList<>();

        double currentABSrisk;

        for(AssetBackedSecurity abs:ABSList){
            currentABSrisk = abs.getRiskValue();
            ABSrisk.add(currentABSrisk);
        }
        return ABSrisk;
    }

    public List<Loan> getLoans(){
        return loans;
    }

    public void setId(int id){
        this.id = id;
        for (Loan loan : loans) {
            loan.setSPVId(id);
        }
    }
    public int getId(){
        return id;
    }

    public void setLoans(List<Loan> loans){
        this.loans = loans;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getSPVriskAverage(){
        return SPVriskAverage;
    }
    public void setSPVriskAverage(double SPVriskAverage){
        this.SPVriskAverage = SPVriskAverage;
    }
    public void setABSList(List<AssetBackedSecurity> ABSList){
        this.ABSList = ABSList;
    }

}


