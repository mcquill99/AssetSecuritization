package ASP;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import util.KeyValueContainer;
import util.ObjectUtils;

import java.util.*;
import java.lang.Math;


public class Bank {
    @JsonIgnore
    public Map<Integer,Borrower> customers;
    @JsonIgnore
    public Map<Integer,Loan> loans;
    @JsonIgnore
    public Map<Loan,Borrower> borrowerLoanPairs;
    @JsonIgnore
    public Map<Integer,SPV> SPVLoanPairs;
    @JsonIgnore
    double balance;

    public Bank(double balance){
        this.balance = balance;
        customers = new HashMap<>();
        loans = new HashMap<>();
        borrowerLoanPairs = new HashMap<>();
        SPVLoanPairs = new HashMap<>();
    }


    public void createLoan(double balance, double interest){

        //create the loan
        Loan loanCreated = new Loan(balance,interest);

        //add the loan to the collection
        loans.put((loans.size()+1),loanCreated);
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
    public void sellLoanToSPV(double expectedInterest, SPV spv) {
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

    //for json
    public Bank(){
        this.balance = 0;
    }
    @JsonProperty("customers")
    public void setCustomers(List<KeyValueContainer<Integer, Borrower>> customers){
        this.customers = ObjectUtils.toMap(customers);
    }
    @JsonProperty("loans")
    public void setLoans(List<KeyValueContainer<Integer, Loan>>  loans){
        this.loans = ObjectUtils.toMap(loans);
    }
    @JsonProperty("borrowerLoanPairs")
    public void setBorrowerLoanPairs(List<KeyValueContainer<Loan, Borrower>>  borrowerLoanPairs){
        this.borrowerLoanPairs = ObjectUtils.toMap(borrowerLoanPairs);
    }
    @JsonProperty("SPVLoanPairs")
    public void setSPVLoanPairs(List<KeyValueContainer<Integer, SPV>>  SPVLoanPairs){
        this.SPVLoanPairs = ObjectUtils.toMap(SPVLoanPairs);
    }
    @JsonProperty("customers")
    public List<KeyValueContainer<Integer,Borrower>> getCustomers(){
        return ObjectUtils.toList(customers);
    }
    @JsonProperty("loans")
    public List<KeyValueContainer<Integer,Loan>> getLoans(){
        return ObjectUtils.toList(loans);
    }
    @JsonProperty("borrowerLoanPairs")
    public List<KeyValueContainer<Loan,Borrower>> getBorrowerLoanPairs(){
        return ObjectUtils.toList(borrowerLoanPairs);
    }
    @JsonProperty("SPVLoanPairs")
    public List<KeyValueContainer<Integer,SPV>> getSPVLoanPairs(){
        return ObjectUtils.toList(SPVLoanPairs);
    }

    @JsonProperty("balance")
    public double getBalance(){
        return this.balance;
    }

    @JsonProperty("balance")
    public void setBalance(double balance){
        this.balance = balance;
    }


}
