package IO;

import ASP.AssetBackedSecurity;
import ASP.Investor;
import ASP.Loan;
import ASP.SPV;

import java.util.Arrays;
import java.util.List;

public class SPVWriter {
    private Loan[] loans;
    private AssetBackedSecurity[] ABSList;
    private Investor[] investors;
    private double balance;
    private double SPVriskAverage;
    private int id = 0;
    private String password;

    public SPVWriter(SPV spv){
        loans = spv.getLoans().toArray(new Loan[0]);
        ABSList = spv.getABSList().toArray(new AssetBackedSecurity[0]);
        balance = spv.getBalance();
        SPVriskAverage = spv.getSPVriskAverage();
        id = spv.getId();
        this.password = spv.getPassword();
        this.investors = spv.getInvestors().toArray(new Investor[0]);
    }

    public SPVWriter(){

    }

    public SPV CreateSPV(){
        SPV toReturn = new SPV(Arrays.asList(loans));
        toReturn.setId(id);
        toReturn.setABSList(Arrays.asList(ABSList));
        toReturn.setBalance(balance);
        toReturn.setSPVriskAverage(SPVriskAverage);
        toReturn.setPassword(password);
        toReturn.setInvestors(Arrays.asList(investors));
        return toReturn;
    }

    public Loan[] getLoans(){
        return loans;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setLoans(Loan[] loans){
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
    public void setABSList(AssetBackedSecurity[] ABSList){
        this.ABSList = ABSList;
    }
    public AssetBackedSecurity[] getABSList() {
        return ABSList;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Investor[] getInvestors(){
        return investors;
    }
    public void setInvestors(Investor[] investors){
        this.investors = investors;
    }

}
