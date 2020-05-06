package IO;

import ASP.AssetBackedSecurity;
import ASP.Investor;

import java.util.ArrayList;
import java.util.Arrays;

public class InvestorWriter {
    private AssetBackedSecurity[] ABSinvestedIn;
    private double balance;
    private int id;
    private String password;

    public InvestorWriter(){

    }
    public InvestorWriter(Investor investor){
        this.ABSinvestedIn = investor.getABSinvestedIn().toArray(new AssetBackedSecurity[0]);
        this.balance = investor.getBalance();
        this.id = investor.getId();
        this.password = investor.getPassword();
    }

    public Investor CreateInvestor(){
        Investor toReturn = new Investor(balance, new ArrayList<>(Arrays.asList(ABSinvestedIn)));
        toReturn.setId(id);
        toReturn.setPassword(password);
        return toReturn;
    }

    public void setABSinvestedIn(AssetBackedSecurity[] ABSinvestedIn){
        this.ABSinvestedIn = ABSinvestedIn;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AssetBackedSecurity[] getABSinvestedIn(){
        return ABSinvestedIn;
    }

    public double getBalance(){
        return balance;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
