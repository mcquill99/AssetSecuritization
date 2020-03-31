package ASP;

public class Borrower {
    public double balance;
    public double loanBalance;

    public Borrower(double balance){
        if (balance < 0) {throw new IllegalArgumentException("Starting balance cannot be negative");}

        this.balance = balance;
    }

    public double checkBalance(){
        return this.balance;
    }

    public void buyLoan(double amount, Bank bankName){

        if (balance >= (amount*.30)) {
            bankName.sellLoan(amount,this);
        }
        else{
            throw new IllegalArgumentException("You need at least 30% of the loan amount to be approved");
        }
    }
}
