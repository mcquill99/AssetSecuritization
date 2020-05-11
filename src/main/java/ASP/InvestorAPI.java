package ASP;

import java.util.List;

public interface InvestorAPI {
    double getBalance();
    List<AssetBackedSecurity> getABSinvestedIn();
    String listABS (SPV spv, double minRisk, double maxRisk ) throws IllegalArgumentException;
    void buyABS(int ABSid, SPV spv, int shares) throws insufficientSharesException;
    String viewMyABS();
    String getPassword();
    int getId();

}
