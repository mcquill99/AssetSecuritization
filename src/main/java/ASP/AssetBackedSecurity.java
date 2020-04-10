package ASP;

import java.util.ArrayList;
import java.util.List;

public class AssetBackedSecurity {

    private List<Loan> loans;
    private double riskValue;

    public AssetBackedSecurity(List<Loan> loansList){
        loans = loansList;
    }

    public void generateRiskValue(){
    }


    public double getRiskValue(){
        return 0;
    }

}
