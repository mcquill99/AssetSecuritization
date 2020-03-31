package ASP;

import java.util.HashMap;
import java.lang.Math;
import java.util.Random;


public class Bank {
    public HashMap<Integer,Borrower> customers = new HashMap<>();
    public HashMap<Integer,Loan> loans = new HashMap<>();
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
        int interest = getRandomIntegerBetweenRange(5,20);

        //create a loan and add it to the collection
        Loan newLoan = new Loan(amount,interest);
        loans.put((loans.size()+1),newLoan);


        borrower.receiveLoan(newLoan);
    }
    public static int getRandomIntegerBetweenRange(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }




}
