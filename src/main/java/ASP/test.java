package ASP;

public class test {
    public static void main(String[] args) {
        Bank chase = new Bank(40000);
        Borrower joel = new Borrower(60);
        Borrower prav = new Borrower(2.50);

        //makes sure joel has $60 before buying the loan

        chase.sellLoan(200, joel);
        //makes sure joel has $260 after taking out the loan
        chase.customers.get(1).checkBalance();
    }
}
