package ASP;

import java.util.ArrayList;
import java.util.List;

public class Investor implements InvestorAPI{
    private List<AssetBackedSecurity> ABSinvestedIn;
    private double balance;

    public Investor(double balance, List ABSinvestedIn){
        this.balance = balance;
        this.ABSinvestedIn = ABSinvestedIn;
    }

    public double getBalance() {
        return balance;
    }

    List<AssetBackedSecurity> catalog = new ArrayList<>();

    public List<AssetBackedSecurity> getABSinvestedIn() {
        return ABSinvestedIn;
    }

    public List<AssetBackedSecurity> listABS (SPV spv, double minRisk, double maxRisk ){
        minRisk = minRisk * .01;
        for (AssetBackedSecurity abs: spv.ABSList){
            if (abs.getRiskValue() >= minRisk && abs.getRiskValue() <= maxRisk ){
                System.out.println("ABSid: " + abs.getId() + ". Associated Risk Value: " + abs.getRiskValue()*100);
                catalog.add(abs);
            }
        }
        return catalog;
    }

    public void buyABS(int ABSid, SPV spv, int shares){
        ABSinvestedIn.add(spv.ABSList.get(ABSid));
    }
}
