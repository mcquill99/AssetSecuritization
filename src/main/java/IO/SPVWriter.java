package IO;

import ASP.AssetBackedSecurity;
import ASP.Loan;
import ASP.SPV;

import java.util.Arrays;
import java.util.List;

public class SPVWriter {
    private Loan[] loans;
    private AssetBackedSecurity[] ABSList;
    private double balance;
    private double SPVriskAverage;
    private int id = 0;

    public SPVWriter(SPV spv){
        loans = spv.getLoans().toArray(new Loan[0]);
        ABSList = spv.getABSList().toArray(new AssetBackedSecurity[0]);
        balance = spv.getBalance();
        SPVriskAverage = spv.getSPVriskAverage();
        id = spv.getId();
    }

    public SPVWriter(){

    }

    public SPV createSPV(){
        SPV toReturn = new SPV(Arrays.asList(loans));
        toReturn.setId(id);
        toReturn.setABSList(Arrays.asList(ABSList));
        toReturn.setBalance(balance);
        toReturn.setSPVriskAverage(SPVriskAverage);
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

}
