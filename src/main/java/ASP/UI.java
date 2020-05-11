package ASP;

import IO.BankWriter;
import IO.InvestorWriter;
import IO.SPVWriter;
import util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private SpvAPI spvAPI;
    private InvestorAPI investorAPI;
    private Scanner read = new Scanner(System.in);
    private Integer id;
    private String password;

    public UI(SpvAPI spvAPI, InvestorAPI investorAPI){
       this.spvAPI = spvAPI;
       this.investorAPI = investorAPI;
    }
    public UI(SpvAPI spvAPI){
        this.spvAPI = spvAPI;
    }

    public void loginPage() throws IOException {
        List<Bank> bankList = new ArrayList<Bank>();
        List<SPV> spvList = new ArrayList<SPV>();
        List<Investor> investorList = new ArrayList<Investor>();
        List<BankWriter> BankList = JsonUtil.listFromJsonFile("src/main/resources/initialBank.json",BankWriter.class);
        List<SPVWriter> SPVList = JsonUtil.listFromJsonFile("src/main/resources/initialSPV.json",SPVWriter.class); //reads lists back in to re create bank and SPVs
        List<InvestorWriter> InvestorList = JsonUtil.listFromJsonFile("src/main/resources/initialInvestor.json", InvestorWriter.class);

        for (int i = 0; i < BankList.size(); i++) {
            bankList.add(BankList.get(i).CreateBank());
        }
        for (int i = 0; i < SPVList.size(); i++) {
            spvList.add(SPVList.get(i).CreateSPV());
        }
        for (int i = 0; i < InvestorList.size(); i++) {
            investorList.add(InvestorList.get(i).CreateInvestor());
        }
        read.reset();
        System.out.println("Hello, Please log in...");
        System.out.println("ID: ");
        id = read.nextInt();
        //int newID = Integer.parseInt(id);
        System.out.println("password: ");
        password = read.next();
        for (int i = 0; i < spvList.size(); i++) {
            if (id == spvList.get(i).getId()){
                SPV spv = spvList.get(i);
                loggedIntoSPV(spv, bankList);
            }
        }
        for (int i = 0; i < investorList.size(); i++) {
            if (id == investorList.get(i).getId()){
                Investor investor = investorList.get(i);
                loggedIntoInvestor(spvList,investor);

            }
        }
    }
    public void loggedIntoSPV(SPV spv, List<Bank> banks) {
        read.useDelimiter("\\n");
        String action;
        double amount;
        double min;
        double max;
        int bankID;
        int numloans;
        System.out.println("WELCOME\n");
        do {
        System.out.println("Your options are: " +
                "\n buyLoan " +
                "\n createABS " +
                "\n viewABSList " +
                "\n viewBankList" +
                "\n viewLoanList" +
                "\n logout \n");

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
                        bankID = bankID-1;
                        Bank bank = banks.get(bankID);
                        spv.buyLoan(amount, bank, spv);
                        System.out.println("Transaction Complete... here is the list of loans owned: \n");
                        List<Loan> loans1 = spv.getLoans();
                        System.out.println("ID:\t " + "Loan Balance:\t " + "Loan Interest:\t " + "In ABS:\t");
                        for (int i = 0; i < loans1.size(); i++) {
                            Loan loan = loans1.get(i);
                            System.out.println( (i + 1)+"\t"+"\t" + loan.getBalance()+"\t" + "\t" + "\t" + loan.getInterest() +"\t" + "\t" + "\t" + "\t" + loan.getIsInAbs());
                        }
                        System.out.println("\n");
                        break;


                    case "createABS":
                        System.out.println("Enter minimum interest level of this ABS");
                        min = read.nextDouble();
                        System.out.println("Enter maximum interest level of this ABS");
                        max = read.nextDouble();
                        System.out.println("Enter number of loans in this ABS");
                        numloans = read.nextInt();
                        spv.createABS(min, max, numloans);
                        System.out.println("Creation Complete, list of ABS: " + spv.getABSList()+"\n");
                        break;

                    case "viewABSList":
                        System.out.println("ABS:\n");
                        System.out.println("ID:\t " + "RiskValue:\t " + "Number of Loans:\t " + "Shares Left to Sell:\t ");
                        for (int i = 0; i < spv.getABSList().size(); i++) {
                            AssetBackedSecurity abs = spv.getABSList().get(i);
                            System.out.println(abs.getId()+"\t"+"\t" + abs.getRiskValue()+"\t"+"\t"+"\t" + abs.numberOfLoans()+"\t"+"\t"+"\t"+"\t" + abs.getSharesLeft());
                        }
                        System.out.println("\n");
                        break;

                    case "viewBankList":
                        System.out.println("Here's a list of Banks:\n");
                        //System.out.println("ID:\t Bank: ");
                        System.out.println("ID:\t "+ " Bank:\t ");
                        for (int i = 0; i < banks.size(); i++) {
                            Bank bank1 = banks.get(i);
                            System.out.println((i+1) +"\t "+ bank1.getLoans());
                        }
                        System.out.println("\n");
                        break;

                    case "viewLoanList":
                        System.out.println("Loan:\n");
                        List<Loan> loans = spv.getLoans();
                        System.out.println("ID:\t " + "Loan Balance:\t " + "Loan Interest:\t " + "In ABS:\t");
                        for (int i = 0; i < loans.size(); i++) {
                            Loan loan = loans.get(i);
                            System.out.println( (i + 1)+"\t"+"\t" + loan.getBalance()+"\t" + "\t" + "\t" + loan.getInterest() +"\t" + "\t" + "\t" + "\t" + loan.getIsInAbs());
                        }
                        System.out.println("\n");

                    case "logout":
                        System.out.println("Have a nice day!");
                        break;

                    default:
                        System.out.println("Please enter a valid input");


                }
            } catch (Exception e) {
                e.printStackTrace();
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
    public void loggedIntoInvestor(List<SPV> spvsList, Investor investor){
        read.useDelimiter("\\n");
        String action;
        int id;
        int spvID;
        int shares;
        double min;
        double max;
        System.out.println("WELCOME\n");
        do {
            System.out.println("Your options are: " +
                    "\n viewSPVList " +
                    "\n buyABS " +
                    "\n listABS " +
                    "\n viewMyABS " +
                    "\n viewABSList " +
                    "\n logout \n");
            action = read.next();
            try {
                switch (action) {
                    case "viewSPVList":
                        for (int i = 0; i < spvsList.size(); i++) {
                            System.out.println("ID:\t" + (i+1) + "\t" + "SPV:\t" + spvsList.get(i));
                        }
                        break;


                    case "buyABS":
                        System.out.println("Enter ABS ID:\n");
                        id = read.nextInt();
                        System.out.println("Enter SPV ID:\n");
                        spvID = read.nextInt();
                        System.out.println("Enter number of shares ou would like to invest:\n");
                        shares = read.nextInt();
                        for (int i = 0; i < spvsList.size(); i++) {
                            if (spvsList.get(spvID-1).getABSList().get(id-1) != null){
                                SPV spv = spvsList.get(spvID-1);
                                investor.buyABS(id,spv,shares);
                            }
                        }
                        System.out.println("You have successfully invested " + shares + " shares in ABS: " + spvsList.get(spvID-1).getABSList().get(id-1));
                        System.out.println(investor.viewMyABS());
                        break;


                    case "listABS":
                        System.out.println("Enter SPV ID:\n");
                        spvID = read.nextInt();
                        System.out.println("Enter Minimum Risk Value:\n");
                        min = read.nextDouble();
                        System.out.println("Enter Maximum Risk Value:\n");
                        max = read.nextDouble();
                        SPV spv = spvsList.get(spvID-1);
                        System.out.println(investor.listABS(spv,min,max));
                        break;

                    case "viewMyABS":
                        System.out.println(investor.viewMyABS());
                        break;

                    case "viewABSList":
                        for (int i = 0; i < spvsList.size(); i++) {
                            SPV spv1 = spvsList.get(i);
                            System.out.println("SPV ID:\t");
                            System.out.println((i+1)+ "\t" + "\t" + spv1);
                            for (int j = 0; j < spv1.getABSList().size(); j++) {
                                System.out.println("ABS ID:\t");
                                System.out.println((j+1)+ "\t" + "\t" + spv1.getABSList());
                            }
                            System.out.println("\n");
                        }

                    case "logout":
                        System.out.println("Have a nice day!");
                        break;

                    default:
                        System.out.println("Please enter a valid input");
                }
            }catch (Exception e) {
                e.printStackTrace();
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
