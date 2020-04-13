package ASP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SPV {
    private List<Loan> loans;
    private List<AssetBackedSecurity> ABSList;
    private HashMap<Loan, Boolean> isInABS = new HashMap<>();
    private double balance;


    public SPV(){
         loans = new ArrayList<>();
         ABSList = new ArrayList<>();
    }

    public SPV(List<Loan> listOfLoans){
        loans = listOfLoans;
        for(Loan loan: loans){
            isInABS.put(loan, false);
        }
    }

    public void buyLoan(){

    }

    public void addABStoList(double minInterest, double maxInterest, int numLoans){

    }

    public AssetBackedSecurity createABS(double minInterest, double maxInterest, int numLoans){
        return null;
    }

    public List<AssetBackedSecurity> getABSList(){
        return ABSList;
    }

    public void AssignRiskValue(){

    }
}
