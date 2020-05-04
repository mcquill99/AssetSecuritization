package ASP;

import java.util.HashMap;

public class Borrower {
    private int id;
    private double balance;
    private HashMap<Integer, Loan> loanMap = new HashMap<>();

    public Borrower(double balance){
        if (balance < 0) {throw new IllegalArgumentException("Starting balance cannot be negative");}

        this.balance = balance;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public double checkBalance(){
        return this.balance;
    }

    public Loan getLoan(int ID){
        return loanMap.get(ID);
    }

    public void buyLoan(double amount, Bank bankName){

        if (balance >= (amount*.30)) {
             bankName.sellLoan(amount,this);
        }
        else{
            throw new IllegalArgumentException("You need at least 30% of the loan amount to be approved");
        }
    }

    public void receiveLoan(Loan newLoan){

        //add the money to the borrowers balance
        balance += newLoan.getBalance();
        loanMap.put(loanMap.size()+1, newLoan);
    }
}
