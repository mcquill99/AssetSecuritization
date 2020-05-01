package ASP;

import java.util.ArrayList;
import java.util.List;

public class AssetBackedSecurity {

    private List<Loan> loans;
    private double riskValue;

    public AssetBackedSecurity(){
        loans = new ArrayList<>();
    }

    public AssetBackedSecurity(List<Loan> loansList){
        loans = loansList;
    }

    public void generateRiskValue(){
        double totalInterest = 0;
        for(Loan loan: loans){
            totalInterest += loan.getInterest();
        }

        riskValue = Math.round((totalInterest / loans.size()) * 100000.0) / 100000.0;
    }


    public double getRiskValue(){
        generateRiskValue();
        return riskValue;
    }
    public List<Loan> getLoanList(){
        return loans;
    }
    public int numberOfLoans(){
        return loans.size();
    }
    // for json processing

    public List<Loan> getLoans(){
        return loans;
    }

    public void setLoans(List<Loan> loans){
        this.loans = loans;
    }

}
