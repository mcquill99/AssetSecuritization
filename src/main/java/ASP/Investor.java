package ASP;

import java.util.ArrayList;
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

    public void listABS (SPV spv, int minRisk, int maxRisk ){
        List<AssetBackedSecurity> catalog = new ArrayList<>();

        for (AssetBackedSecurity abs: spv.ABSList ){
            if (abs.getRiskValue() >= minRisk && abs.getRiskValue() <= maxRisk ){
                catalog.add(abs);
            }
        }
    }
}
