package ASP;

import java.util.HashMap;
import java.util.List;

public class Investor {
    private List<AssetBackedSecurity> ABSinvestedIn;
    private double balance;

    public Investor(double balance, List ABSinvestedIn){
        this.balance = balance;
        this.ABSinvestedIn = ABSinvestedIn;
    }

}
