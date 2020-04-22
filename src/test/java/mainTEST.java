import ASP.*;

public class mainTEST {

    public static void main(String[] args) throws insufficientLoansException {
        Borrower Sean = new Borrower(6000);
        Bank ChaseBank = new Bank(10000000);
        SPV spv = new SPV();
        ChaseBank.createLoan(1000,5.0);
        ChaseBank.createLoan(2500,6.0);
        AssetBackedSecurity asp = new AssetBackedSecurity();

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

        //PROBLEM:takes in whole numbers as interest rates instead of decimal format

        //spv.createABS(5.0,6.0,2);
        //spv.getABSList();


    }

}