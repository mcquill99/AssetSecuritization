import ASP.*;
import IO.BankWriter;
import IO.InvestorWriter;
import IO.SPVWriter;
import util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest2 {
    public static void main(String[] args) throws insufficientLoansException, insufficientSharesException, IOException {
        Bank bank1 = new Bank(100000); //Creates bank and loans
        bank1.createLoan(500,5);
        bank1.createLoan(100,5);
        bank1.createLoan(1000,5);
        bank1.createLoan(50,5);
        bank1.createLoan(1500,5);
        bank1.createLoan(500,10);
        bank1.createLoan(800,10);
        bank1.createLoan(750,10);
        bank1.createLoan(550,7.5);
        bank1.createLoan(501,7.5);
        bank1.createLoan(550,7.5);
        bank1.createLoan(501,6);
        bank1.createLoan(505,8);
        bank1.createLoan(505,8);
        bank1.createLoan(500,15);
        bank1.createLoan(500,15);
        bank1.createLoan(500,20);
        bank1.createLoan(500,12.5);
        bank1.createLoan(5000,12.5);
        bank1.createLoan(500,15);
        bank1.createLoan(500,90);

        SPV spv1 = new SPV(); //creates spv and buys loans to group SPVs
        spv1.setPassword("password");
        spv1.setId(5);
        spv1.buyLoan(5, bank1, spv1);
        spv1.buyLoan(10, bank1, spv1);
        spv1.buyLoan(5, bank1, spv1);
        spv1.buyLoan(10, bank1, spv1);
        spv1.buyLoan(10, bank1, spv1);
        spv1.createABS(5,10,5);
        System.out.println("SPV1 ID: " + spv1.getId());
        System.out.println("SPV1's ABS");
        System.out.println("********************************");
        System.out.println("ID: " + spv1.getABSList().get(0).getId());
        System.out.println("Risk value: " + spv1.getABSList().get(0).getRiskValue());
        System.out.println("Loans: " );
        System.out.println("Balance | Interest");
        for(Loan loan : spv1.getABSList().get(0).getLoans()){
            System.out.println("$"+loan.getBalance() + " | " + loan.getInterest());
        }
        System.out.println("--------------------------------");

        SPV spv2 = new SPV();
        spv2.setPassword("pass123");
        spv2.setId(10);
        spv2.buyLoan(15, bank1, spv2);
        spv2.buyLoan(15, bank1, spv2);
        spv2.buyLoan(20, bank1, spv2);
        spv2.buyLoan(12.5, bank1, spv2);
        spv2.buyLoan(12.5, bank1, spv2);
        spv2.createABS(10,20,5);

        System.out.println("SPV2 ID: " + spv2.getId());
        System.out.println("SPV2's ABS");
        System.out.println("********************************");
        System.out.println("ID: " + spv2.getABSList().get(0).getId());
        System.out.println("Risk value: " + spv2.getABSList().get(0).getRiskValue());
        System.out.println("Loans: " );
        System.out.println("Balance | Interest");
        for(Loan loan : spv2.getABSList().get(0).getLoans()){
            System.out.println("$"+loan.getBalance() + " | " + loan.getInterest());
        }
        System.out.println("--------------------------------");

        Investor investor1 = new Investor(500, new ArrayList<>());
        investor1.setId(50);
        investor1.buyABS(1, spv1, 30);
        System.out.println("INVESTOR ID: " + investor1.getId());
        System.out.println("INVESTOR BALANCE: $" + investor1.getBalance());
        System.out.println("Investor1's investments");
        System.out.println("********************************");
        System.out.println("ABS ID: " + investor1.getABSinvestedIn().get(0).getId());
        System.out.println("SPV ID who owns it: " + investor1.getABSinvestedIn().get(0).getSpvid());
        System.out.println("ABS Risk Value: " + investor1.getABSinvestedIn().get(0).getRiskValue());
        System.out.println("Shares: " + investor1.getABSinvestedIn().get(0).getInvestorMap().get(investor1.getId()));
        System.out.println("--------------------------------");

        Investor investor2 = new Investor(1000, new ArrayList<>());
        investor2.setId(66);
        investor2.buyABS(1, spv1, 15);
        //investor2.buyABS(1,spv2,40);
        System.out.println("INVESTOR ID: " + investor2.getId());
        System.out.println("INVESTOR BALANCE: $" + investor2.getBalance());
        System.out.println("Investor2's investments");
        System.out.println("********************************");
        System.out.println("ABS ID: " + investor2.getABSinvestedIn().get(0).getId());
        System.out.println("SPV ID who owns it: " + investor2.getABSinvestedIn().get(0).getSpvid());
        System.out.println("ABS Risk Value: " + investor2.getABSinvestedIn().get(0).getRiskValue());
        System.out.println("Shares: " + investor2.getABSinvestedIn().get(0).getInvestorMap().get(investor2.getId()));
        System.out.println("--------------------------------");

        System.out.println("WRITING TO JSON");

        BankWriter bank1Writer = new BankWriter(bank1); //creates Writers to write the Bank and 2 SPVs to files

        SPVWriter spv1Writer = new SPVWriter(spv1);
        SPVWriter spv2Writer = new SPVWriter(spv2);

        InvestorWriter investor1Writer = new InvestorWriter(investor1);
        InvestorWriter investor2Writer = new InvestorWriter(investor2);

        SPVWriter[] spvArray = new SPVWriter[] {spv1Writer,spv2Writer}; //Adds them to Arrays to be written to JSON with, so all can be pulled into one list and stored in just 1 file per class
        BankWriter[] bankArray = new BankWriter[] {bank1Writer};
        InvestorWriter[] investorArray = new InvestorWriter[] {investor1Writer, investor2Writer};

        JsonUtil.toJsonFile("src/main/resources/initialSPV.json", spvArray);
        JsonUtil.toJsonFile("src/main/resources/initialBank.json", bankArray);
        JsonUtil.toJsonFile("src/main/resources/initialInvestor.json", investorArray);

        System.out.println("--------------------------------");
        System.out.println("COMPLETE, READING FROM JSON");
        System.out.println("--------------------------------");

        List<BankWriter> BankWriterList = JsonUtil.listFromJsonFile("src/main/resources/initialBank.json",BankWriter.class);
        List<SPVWriter> SPVWriterList =  JsonUtil.listFromJsonFile("src/main/resources/initialSPV.json",SPVWriter.class); //reads lists back in to re create bank and SPVs
        List<InvestorWriter> InvestorWriterList = JsonUtil.listFromJsonFile("src/main/resources/initialInvestor.json",InvestorWriter.class);
        List<SPV> SPVList = new ArrayList<>();
        List<Bank> BankList = new ArrayList<>();
        List<Investor> InvestorList = new ArrayList<>();

        for(InvestorWriter writer : InvestorWriterList){ //creates lists of all data read in
            InvestorList.add(writer.CreateInvestor());
        }

        for(BankWriter writer : BankWriterList){
            BankList.add(writer.CreateBank());
        }
        for(SPVWriter writer : SPVWriterList){
            SPV spvToAdd = writer.CreateSPV();
            spvToAdd.setInvestors(InvestorList); //replacing investors list with current objects
            SPVList.add(spvToAdd);
        }





        Bank bankFromJSON = BankList.get(0); //creates the Bank /SPVs from the Writer classes
        SPV spv1FromJSON = SPVList.get(0);
        SPV spv2FromJSON = SPVList.get(1);

        Investor investor1FromJSON = InvestorList.get(0);
        Investor investor2FromJSON = InvestorList.get(1);

        System.out.println("SPV1 FROM JSON ID: " + spv1FromJSON.getId());
        System.out.println("SPV1 FROM JSON's ABS");
        System.out.println("********************************");
        System.out.println("ID: " + spv1FromJSON.getABSList().get(0).getId());
        System.out.println("Risk value: " + spv1FromJSON.getABSList().get(0).getRiskValue());
        System.out.println("Loans: " );
        System.out.println("Balance | Interest");
        for(Loan loan : spv1FromJSON.getABSList().get(0).getLoans()){
            System.out.println("$"+loan.getBalance() + " | " + loan.getInterest());
        }
        System.out.println("--------------------------------");

        System.out.println("SPV2 FROM JSON ID: " + spv2FromJSON.getId());
        System.out.println("SPV2 FROM JSON's ABS");
        System.out.println("********************************");
        System.out.println("ID: " + spv2FromJSON.getABSList().get(0).getId());
        System.out.println("Risk value: " + spv2FromJSON.getABSList().get(0).getRiskValue());
        System.out.println("Loans: " );
        System.out.println("Balance | Interest");
        for(Loan loan : spv2FromJSON.getABSList().get(0).getLoans()){
            System.out.println("$"+loan.getBalance() + " | " + loan.getInterest());
        }
        System.out.println("--------------------------------");

        System.out.println("INVESTOR ID: " + investor1FromJSON.getId());
        System.out.println("INVESTOR BALANCE: $" + investor1FromJSON.getBalance());
        System.out.println("Investor1 FROM JSON's investments");
        System.out.println("********************************");
        System.out.println("ABS ID: " + investor1FromJSON.getABSinvestedIn().get(0).getId());
        System.out.println("SPV ID who owns it: " + investor1FromJSON.getABSinvestedIn().get(0).getSpvid());
        System.out.println("ABS Risk Value: " + investor1FromJSON.getABSinvestedIn().get(0).getRiskValue());
        System.out.println("Shares: " + investor1FromJSON.getABSinvestedIn().get(0).getInvestorMap().get(investor1FromJSON.getId()));
        System.out.println("--------------------------------");

        System.out.println("INVESTOR ID: " + investor2FromJSON.getId());
        System.out.println("INVESTOR BALANCE: $" + investor2FromJSON.getBalance());
        System.out.println("Investor1 FROM JSON's investments");
        System.out.println("********************************");
        System.out.println("ABS ID: " + investor2FromJSON.getABSinvestedIn().get(0).getId());
        System.out.println("SPV ID who owns it: " + investor2FromJSON.getABSinvestedIn().get(0).getSpvid());
        System.out.println("ABS Risk Value: " + investor2FromJSON.getABSinvestedIn().get(0).getRiskValue());
        System.out.println("Shares: " + investor1FromJSON.getABSinvestedIn().get(0).getInvestorMap().get(investor2FromJSON.getId()));
        System.out.println("--------------------------------");



        //spv1FromJSON.buyLoan(90, bankFromJSON, spv1FromJSON);
        //List<Loan> loans = spv1FromJSON.getLoans(); //testing if they can buy

        investor1FromJSON.buyABS(1, spv2FromJSON, 20);
        System.out.println("Investor 1 FROM JSON is buying 20 shares from SPV2s ABS");
        System.out.println("--------------------------------");
        System.out.println("INVESTOR ID: " + investor1FromJSON.getId());
        System.out.println("INVESTOR BALANCE: $" + investor1FromJSON.getBalance());
        System.out.println("Investor1 FROM JSON's investments");
        System.out.println("********************************");
        System.out.println("ABS ID: " + investor1FromJSON.getABSinvestedIn().get(0).getId());
        System.out.println("SPV ID who owns it: " + investor1FromJSON.getABSinvestedIn().get(0).getSpvid());
        System.out.println("ABS Risk Value: " + investor1FromJSON.getABSinvestedIn().get(0).getRiskValue());
        System.out.println("Shares: " + investor1FromJSON.getABSinvestedIn().get(0).getInvestorMap().get(investor1FromJSON.getId()));
        System.out.println("********************************");
        System.out.println("ABS ID: " + investor1FromJSON.getABSinvestedIn().get(1).getId());
        System.out.println("SPV ID who owns it: " + investor1FromJSON.getABSinvestedIn().get(1).getSpvid());
        System.out.println("ABS Risk Value: " + investor1FromJSON.getABSinvestedIn().get(1).getRiskValue());
        System.out.println("Shares: " + investor1FromJSON.getABSinvestedIn().get(1).getInvestorMap().get(investor1FromJSON.getId()));
        System.out.println("--------------------------------");


        spv1FromJSON.payInvestors(); //testing that they can pay out to investors
        System.out.println("SPV1 From JSON has now paid out to it's investors");
        System.out.println("--------------------------------");
        System.out.println("Investor 1 FROM JSON new Balance: $" + investor1FromJSON.getBalance());
        System.out.println("Investor 2 FROM JSON new Balance: $" + investor2FromJSON.getBalance());
        System.out.println("--------------------------------");
        spv2FromJSON.payInvestors();
        System.out.println("SPV2 From JSON has now paid out to it's investors");
        System.out.println("--------------------------------");
        System.out.println("Investor 1 FROM JSON new Balance: $" + investor1FromJSON.getBalance());
        System.out.println("Investor 2 FROM JSON new Balance: $" + investor2FromJSON.getBalance());
        System.out.println("--------------------------------");
        System.out.println("Done");
    }

}
