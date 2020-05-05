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

    public List<AssetBackedSecurity> getABSinvestedIn() {
        return ABSinvestedIn;
    }

    public void listABS (SPV spv, int minRisk, int maxRisk ){

        for (AssetBackedSecurity abs: spv.getABSList()){
            if (abs.getRiskValue() >= minRisk && abs.getRiskValue() <= maxRisk ){
                System.out.println(abs.getId());
                System.out.println(abs.getRiskValue());

            }
        }
    }

    public void buyABS(int ABSid, SPV spv, int shares){
        ABSinvestedIn.add(spv.ABSList.get(ABSid));
    }
}
