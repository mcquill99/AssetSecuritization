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
        double totalInterest = 0;
        for(Loan loan: loans){
            totalInterest += loan.getInterest();
        }

        riskValue = totalInterest / loans.size();
    }


    public double getRiskValue(){
        return riskValue;
    }

}
