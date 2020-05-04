package ASP;

public class Loan {
    private double interest;
    private double balance;
    private int id;
    private int borrowerId = 0;
    private int SPVId = 0;
    private boolean isInAbs = false;

    public Loan(double balance, double interest){
        if(!isValidAmount(balance) || !isValidAmount(interest)){ //check if num is valid
            throw new IllegalArgumentException("Interest and balance must be positive");
        }
        this.interest = interest / 100;
        this.balance = balance;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getBorrowerId(){
        return borrowerId;
    }
    public void setBorrowerId(int borrowerId){
        this.borrowerId = borrowerId;
    }

    public int getSPVId(){
        return SPVId;
    }
    public void setSPVId(int SPVId){
        this.SPVId = SPVId;
    }

    public boolean getIsInAbs(){
        return isInAbs;
    }
    public void setIsInAbs(boolean bool){
        isInAbs = bool;
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

    //for json library
    public Loan(){

    }

    public void setInterest(double interest){
        this.interest = interest;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}
