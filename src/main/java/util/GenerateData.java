package util;

import ASP.*;
import IO.BankWriter;
import IO.InvestorWriter;
import IO.SPVWriter;

import javax.lang.model.type.ArrayType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateData {

    public static void initialTest() throws insufficientLoansException, IOException, insufficientSharesException {
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

        SPV spv2 = new SPV();
        spv2.setPassword("pass123");
        spv2.setId(10);
        spv2.buyLoan(15, bank1, spv2);
        spv2.buyLoan(15, bank1, spv2);
        spv2.buyLoan(20, bank1, spv2);
        spv2.buyLoan(12.5, bank1, spv2);
        spv2.buyLoan(12.5, bank1, spv2);
        spv2.createABS(10,20,5);

        Investor investor1 = new Investor(500, new ArrayList<>());
        investor1.setId(50);
        investor1.buyABS(1, spv1, 30);

        Investor investor2 = new Investor(1000, new ArrayList<>());
        investor2.setId(69);
        investor2.buyABS(1, spv1, 15);
        //investor2.buyABS(1,spv2,40);

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

        spv1FromJSON.buyLoan(90, bankFromJSON, spv1FromJSON);
        List<Loan> loans = spv1FromJSON.getLoans(); //testing if they can buy

        investor1FromJSON.buyABS(1, spv2FromJSON, 20);
        spv1FromJSON.payInvestors(); //testing that they can pay out to investors
        spv2FromJSON.payInvestors();
        System.out.println("Done");
    }

    public static void main(String[] args) throws insufficientLoansException, IOException, insufficientSharesException {
        initialTest();
    }
}
