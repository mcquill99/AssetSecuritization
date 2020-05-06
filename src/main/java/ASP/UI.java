package ASP;

import IO.BankWriter;
import IO.SPVWriter;
import util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private SpvAPI spvAPI;
    //private InvestorAPI investorAPI;
    private Scanner read = new Scanner(System.in);
    private String id, password;

//    public UI(SpvAPI spvAPI, InvestorAPI investorAPI){
//        this.spvAPI = spvAPI;
//        this.investorAPI = investorAPI;
//    }
    public UI(SpvAPI spvAPI){
        this.spvAPI = spvAPI;
    }

    public void loginPage() throws IOException {
        List<Bank> bankList = new ArrayList<Bank>();
        List<SPV> spvList = new ArrayList<SPV>();
        List<BankWriter> BankList = JsonUtil.listFromJsonFile("src/main/resources/initialBank.json",BankWriter.class);
        List<SPVWriter> SPVList =  JsonUtil.listFromJsonFile("src/main/resources/initialSPV.json",SPVWriter.class); //reads lists back in to re create bank and SPVs

        for (int i = 0; i < BankList.size(); i++) {
            bankList.add(BankList.get(i).CreateBank());
        }
        for (int i = 0; i < BankList.size(); i++) {
            spvList.add(SPVList.get(i).CreateSPV());
        }
        read.reset();
        System.out.println("Hello, Please log in...");
        System.out.println("ID: ");
        id = read.next();
        int newID = Integer.parseInt(id);
        System.out.println("password: ");
        password = read.next();
        for (int i = 0; i < spvList.size(); i++) {
            if (newID == spvList.get(i).getId()){
                SPV spv = spvList.get(i);
                loggedIntoSPV(spv, bankList);
            }
        }
//        loggedIntoInvestor();
    }
    public void loggedIntoSPV(SPV spv, List<Bank> banks) {
        read.useDelimiter("\\n");
        String action;
        double amount;
        double min;
        double max;
        int bankID;
        int numloans;
        System.out.println("Your current balance is: " + spv.getBalance());


        do {
            System.out.println("Please enter an action or type 'help' for a list of commands");
            action = read.next();
            //action = action.toLowerCase();
            try {
                switch (action) {
                    case "help":
                        System.out.println("Your options are: " +
                                "\n buyLoan " +
                                "\n createABS " +
                                "\n viewABSList " +
                                "\n viewBankList" +
                                "\n viewLoanList" +
                                "\n logout \n");
                        break;
                    case "buyLoan":
                        System.out.println("Enter interest rate you are looking for: ");
                        amount = read.nextDouble();
                        System.out.println("Enter Bank ID your are trying to buy from: ");
                        bankID = read.nextInt();
                        int newBankID = bankID-1;
                        Bank bank = banks.get(newBankID);
                        spvAPI.buyLoan(amount, bank, spv);
                        System.out.println("Transaction Complete... here is the list of loans owned: " + spvAPI.getLoans());
                        break;


                    case "createABS":
                        System.out.println("Enter minimum interest level of this ABS");
                        min = read.nextDouble();
                        System.out.println("Enter maximum interest level of this ABS");
                        max = read.nextDouble();
                        System.out.println("Enter number of loans in this ABS");
                        numloans = read.nextInt();
                        spvAPI.createABS(min, max, numloans);
                        System.out.println("Creation Complete, list of ABS: " + spvAPI.getABSList());
                        break;

                    case "viewABSList":
                        System.out.println("ABS:\n");
                        System.out.println(spvAPI.getABSList());

                    case "viewBankList":
                        System.out.println("Here's a list of Banks:\n");
                        //System.out.println("ID:\t Bank: ");
                        for (int i = 0; i < banks.size(); i++) {
                            System.out.println("ID:\t "+(i+1) + " Bank:\t "+ banks.get(i));
                        }
                        break;

                    case "viewLoanList":
                        System.out.println("Loan:\n");
                        List<Loan> loans = spvAPI.getLoans();
                        for (int i = 0; i < loans.size(); i++) {
                            System.out.println();
                        }


                    case "logout":
                        System.out.println("Have a nice day!");
                        break;

                    default:
                        System.out.println("Please enter a valid input");


                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (!action.equals("logout"));
        try {
            loginPage();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
