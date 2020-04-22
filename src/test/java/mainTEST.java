import ASP.*;

public class mainTEST {

    public static void main(String[] args) throws insufficientLoansException {
        Borrower Sean = new Borrower(6000);
        Borrower Joel = new Borrower(6000);

        Bank ChaseBank = new Bank(10000000);
        SPV spv = new SPV();
        ChaseBank.createLoan(1000,5.0);
        ChaseBank.createLoan(2500,6.0);

        System.out.println("Printing Borrower's balance before loan purchase:\n");
        System.out.println("Sean:" + Sean.checkBalance());
        System.out.println("Joel:" + Joel.checkBalance());
        System.out.println(" ");

        Sean.buyLoan(1000,ChaseBank);
        Joel.buyLoan(2500,ChaseBank);

        System.out.println("Printing Borrower's balance after loan purchase:\n");
        System.out.println("Sean:" + Sean.checkBalance());
        System.out.println("Joel:" + Joel.checkBalance());

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Should contain 2 loans:\n");

        System.out.println(ChaseBank.loans);
        System.out.println(" ");

        spv.buyLoan(5.0,ChaseBank,spv);

        System.out.println("Should contain 1 loan:\n");
        System.out.println(ChaseBank.loans);
        System.out.println(" ");
        System.out.println("Should contain 1 loan:\n");
        System.out.println(spv.getLoans());

        spv.buyLoan(6.0,ChaseBank,spv);
        System.out.println(" ");

        System.out.println("Should contain 0 loan:\n");
        System.out.println(ChaseBank.loans);
        System.out.println(" ");
        System.out.println("Should contain 2 loan:\n");
        System.out.println(spv.getLoans());
        System.out.println(" ");
        System.out.println(" ");

        spv.createABS(5.0,6.0,2);
        System.out.println(spv.getABSList());


    }

}