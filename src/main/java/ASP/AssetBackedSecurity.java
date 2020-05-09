package ASP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetBackedSecurity {

    private List<Loan> loans;
    private double riskValue;
    private int id;
    private int spvid = 0;
    private int sharesLeft = 100;
    private double balance = 0;
    private Map<Integer, Integer> investorMap = new HashMap<>();

    public AssetBackedSecurity(){
        loans = new ArrayList<>();
    }

    public AssetBackedSecurity(List<Loan> loansList){
        loans = loansList;
        for(Loan loan : loansList){
            this.balance += loan.getBalance();
        }
    }

    public void generateRiskValue(){
        double totalInterest = 0;
        for(Loan loan: loans){
            totalInterest += loan.getInterest();
        }

        riskValue = Math.round((totalInterest / loans.size()) * 100000.0) / 100000.0;
    }


    public double getRiskValue(){
        generateRiskValue();
        return riskValue;
    }
    public int numberOfLoans(){
        return loans.size();
    }
    // for json processing

    public List<Loan> getLoans(){
        return loans;
    }

    public void setLoans(List<Loan> loans){
        this.loans = loans;
    }

    public void setSpvid(int id){
        this.spvid = id;
    }

    public int getSpvid(){
        return spvid;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public int getSharesLeft(){
        return sharesLeft;
    }

    public void setSharesLeft(int sharesLeft){
        this.sharesLeft = sharesLeft;
    }

    public void addInvestor(int id, int shares){
        investorMap.put(id, shares);
    }

    public Map<Integer,Integer> getInvestorMap(){
        return investorMap;
    }

    public void setInvestorMap(Map<Integer, Integer> investorMap){
        this.investorMap = investorMap;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

}
