import ASP.*;

import java.nio.charset.CharsetDecoder;


public class mainTEST {

    public static void main(String[] args) {
        Borrower Sean = new Borrower(6000);
        Bank ChaseBank = new Bank(10000000);
        SPV spv = new SPV();
        ChaseBank.createLoan(1000,5.0);
        ChaseBank.createLoan(2500,6.0);

        System.out.println("Should contain 2 loans");

        System.out.println(ChaseBank.loans);
        System.out.println(" ");

        spv.buyLoan(5.0,ChaseBank,spv);

        System.out.println("Should contain 1 loan");
        System.out.println(ChaseBank.loans);
        System.out.println(" ");
        System.out.println("Should contain 1 loan");
        System.out.println(spv.loan);
    }

}