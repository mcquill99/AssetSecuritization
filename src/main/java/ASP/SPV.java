package ASP;

import javax.naming.InsufficientResourcesException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SPV implements SpvAPI{
    private List<Loan> loans;
    public List<AssetBackedSecurity> ABSList = new ArrayList<>();
    private List<Investor> investors = new ArrayList<>();
    private double balance = 0;
    public double SPVriskAverage;
    private int id = 0;
    private String password = "";


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
        absToAdd.setSpvid(id);
        absToAdd.setId(ABSList.size() + 1);
        ABSList.add(absToAdd);

        return absToAdd;
    }

    public void sellABSToInvestor(Investor investor,int ABSid, int shares) throws insufficientSharesException, IllegalArgumentException {
        AssetBackedSecurity absToSell = null;
        for(AssetBackedSecurity ABS : ABSList){
            if(ABS.getId() == ABSid){
                absToSell = ABS;
                break;
            }
        }
        if(absToSell == null){
            throw new IllegalArgumentException("Please enter a valid ABS ID");
        }
        int currentShares = absToSell.getSharesLeft();
        if(currentShares - shares >= 0){
            investors.add(investor);
            absToSell.addInvestor(investor.getId(), shares);
            absToSell.setSharesLeft(currentShares - shares);
            investor.getABSinvestedIn().add(absToSell);
        }
        else{
            throw new insufficientSharesException("Not enough shares are left in that ABS, only " + currentShares + "are left");
        }

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
    public String getPassword(){
        return password;
    }
    public void setPassword(String pass){
        this.password = pass;
    }

    public void setInvestors(List<Investor> investors){
        this.investors = investors;
    }
    public List<Investor> getInvestors(){
        return investors;
    }

}


