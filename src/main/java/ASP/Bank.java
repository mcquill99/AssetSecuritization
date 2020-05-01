package ASP;

import java.util.HashMap;
import java.lang.Math;
import java.util.Map;
import java.util.Random;
import java.util.Iterator;


public class Bank {
    public HashMap<Integer,Borrower> customers = new HashMap<>();
    public HashMap<Integer,Loan> loans = new HashMap<>();
    public HashMap<Loan,Borrower> borrowerLoanPairs = new HashMap<>();
    public HashMap<Integer,SPV> SPVLoanPairs = new HashMap<>();

    double balance;

    public Bank(double balance){
        this.balance = balance;
    }

    public void createLoan(double balance, double interest){

        //create the loan
        Loan loanCreated = new Loan(balance,interest);

        //add the loan to the collection
        loans.put((loans.size()+1),loanCreated);
    }

    public double checkBalance(){
        return this.balance;
    }

    public void releaseAsset(int loanID){
        Loan loanCreated = new Loan(20,20);

        if (loans.containsKey(loanID)){
            loans.remove(loanID);
        }
        else {
            throw new IllegalArgumentException("LoanID is not found in the collection of loans.");
        }
    }

    public void sellLoan(double amount, Borrower borrower){
        //add the customer to our borrower collection
        customers.put((customers.size()+1),borrower);

        //generate an interest rate
        int interest = generateInterestRate(5,20);

        //create a loan and add it to the collection
        Loan newLoan = new Loan(amount,interest);
        loans.put((loans.size()+1),newLoan);

        //add the customer loan pair to our map
        borrowerLoanPairs.put(newLoan, borrower);


        borrower.receiveLoan(newLoan);
    }
    void sellLoanToSPV(double expectedInterest, SPV spv) {
        expectedInterest = expectedInterest/100;
        Iterator<HashMap.Entry<Integer, Loan>> itr = loans.entrySet().iterator();
        int loanID = 0;
        Loan boughtLoan;
        while (itr.hasNext()) {
            HashMap.Entry<Integer, Loan> loanToLookFor = itr.next();
            if (loanToLookFor.getValue().getInterest() == expectedInterest) {
                loanID = loanToLookFor.getKey();
            }
        }
        boughtLoan = loans.get(loanID);
        SPVLoanPairs.put(loanID,spv);
        spv.getLoans().add(boughtLoan);
        spv.isInABS.put(boughtLoan,false);
        loans.remove(loanID);
    }
    public static int generateInterestRate(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }


}
