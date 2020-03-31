package ASP;

public class Loan {
    private double interest;
    private double balance;

    public Loan(double balance, double interest){
        if(!isValidAmount(balance) || !isValidAmount(interest)){ //check if num is valid
            throw new IllegalArgumentException("Interest and balance must be positive");
        }
        this.interest = interest / 100;
        this.balance = balance;
    }

    public void accrueInterest(){
        double amount = balance * interest;
        balance += amount;
        balance = Math.round(balance * 100.0) / 100.0;
    }
    public double getInterest(){
        return interest;
    }

    public double getBalance(){
        return balance;
    }

    public void subtractFromTotal(double amount){
        boolean isValid = isValidAmount(amount);
        if(amount <= balance && isValid){
            balance = balance - amount;
        }
        else{
            throw new IllegalArgumentException("Amount must be less than or equal to balance and must have at most 2 decimal places");
        }
    }

    //valid amount function
    public boolean isValidAmount(double amount){
        double rounded = Math.round(amount * 100.0) / 100.0;
        return amount == rounded && amount > 0;
    }
}
