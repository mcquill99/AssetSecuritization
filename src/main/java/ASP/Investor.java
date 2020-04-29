package ASP;

import java.util.List;

public class Investor {
    private List<AssetBackedSecurity> ABSinvestedIn;
    private double balance;

    public Investor(double balance, List ABSinvestedIn){
        this.balance = balance;
        this.ABSinvestedIn = ABSinvestedIn;
    }

    public double getBalance() {
        return balance;
    }

    public List<AssetBackedSecurity> getABSinvestedIn() {
        return ABSinvestedIn;
    }
}
