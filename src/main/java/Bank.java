import java.util.ArrayList;

public class Bank {
    private ArrayList<Loan> loans;

    public Bank(){

    }

    public Loan createLoan(double balance, double interest) throws IllegalArgumentException{
        return new Loan(balance, interest);
    }
}
