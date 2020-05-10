package ASP;

import java.util.HashMap;
import java.lang.Math;
import java.util.Map;
import java.util.Random;
import java.util.Iterator;


public class Bank {
    public Map<Integer,Borrower> customers = new HashMap<>();
    public Map<Integer,Loan> loans = new HashMap<>();
    public Map<Loan,Borrower> borrowerLoanPairs = new HashMap<>();
    public Map<Integer,SPV> SPVLoanPairs = new HashMap<>();

    double balance;

    public Bank(double balance){
        this.balance = balance;
    }

    public void createLoan(double balance, double interest){

        //create the loan
        Loan loanCreated = new Loan(balance,interest);
        loanCreated.setId((loans.size()+1));

        //add the loan to the collection
        loans.put(loanCreated.getId(),loanCreated);
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
        if(!customers.containsValue(borrower)){
            //add the customer to our borrower collection
            borrower.setId(customers.size()+1);
            customers.put(borrower.getId(),borrower);
        }

        //generate an interest rate
        int interest = generateInterestRate(5,20);

        //create a loan and add it to the collection
        Loan newLoan = new Loan(amount,interest);
        newLoan.setId(loans.size()+1);
        loans.put(newLoan.getId(),newLoan);


        //add the customer loan pair to our map
        newLoan.setBorrowerId(borrower.getId());
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
            if (loanToLookFor.getValue().getInterest() == expectedInterest && !SPVLoanPairs.containsKey(loanToLookFor.getKey())) {
                loanID = loanToLookFor.getKey();
            }
        }
        boughtLoan = loans.get(loanID);
        if(spv.getId() == 0){
            spv.setId(SPVLoanPairs.size()+1);
        }
        boughtLoan.setSPVId(spv.getId());
        SPVLoanPairs.put(loanID,spv);
        spv.getLoans().add(boughtLoan);
        //spv.isInABS.put(boughtLoan,false);
        //loans.remove(loanID);
    }
    public static int generateInterestRate(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

    //for json
    public Bank(){
        balance = 0;
    }
    public void setCustomers(Map<Integer, Borrower> customers){
        this.customers = customers;
    }
    public void setLoans(Map<Integer, Loan> loans){
        this.loans = loans;
    }
    public void setBorrowerLoanPairs(Map<Loan, Borrower> borrowerLoanPairs){
        this.borrowerLoanPairs = borrowerLoanPairs;
    }
    public void setSPVLoanPairs(Map<Integer, SPV> SPVLoanPairs){
        this.SPVLoanPairs = SPVLoanPairs;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public Map<Integer, Borrower> getCustomers(){
        return customers;
    }
    public Map<Integer, Loan> getLoans(){
        return loans;
    }
    public Map<Loan, Borrower> getBorrowerLoanPairs(){
        return borrowerLoanPairs;
    }
    public Map<Integer, SPV> getSPVLoanPairs(){
        return SPVLoanPairs;
    }
    public double getBalance(){
        return balance;
}



}
