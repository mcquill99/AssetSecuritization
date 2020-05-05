package util;

import ASP.Bank;
import ASP.SPV;
import ASP.insufficientLoansException;
import IO.BankWriter;
import IO.SPVWriter;

import java.io.IOException;
import java.util.List;

public class GenerateData {
    public static void initialTest() throws insufficientLoansException, IOException {
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

        SPV spv1 = new SPV(); //creates spv and buys loans to group SPVs
        spv1.setId(5);
        spv1.buyLoan(5, bank1, spv1);
        spv1.buyLoan(10, bank1, spv1);
        spv1.buyLoan(5, bank1, spv1);
        spv1.buyLoan(10, bank1, spv1);
        spv1.buyLoan(10, bank1, spv1);
        spv1.createABS(5,10,5);

        SPV spv2 = new SPV();
        spv2.setId(10);
        spv2.buyLoan(15, bank1, spv2);
        spv2.buyLoan(15, bank1, spv2);
        spv2.buyLoan(20, bank1, spv2);
        spv2.buyLoan(12.5, bank1, spv2);
        spv2.buyLoan(12.5, bank1, spv2);
        spv2.createABS(10,20,5);

        BankWriter bank1Writer = new BankWriter(bank1); //creates Writers to write the Bank and 2 SPVs to files

        SPVWriter spv1Writer = new SPVWriter(spv1);
        SPVWriter spv2Writer = new SPVWriter(spv2);

        SPVWriter[] spvArray = new SPVWriter[] {spv1Writer,spv2Writer}; //Adds them to Arrays to be written to JSON with, so all can be pulled into one list and stored in just 1 file per class
        BankWriter[] bankArray = new BankWriter[] {bank1Writer};
        JsonUtil.toJsonFile("src/main/resources/initialSPV.json", spvArray);
        JsonUtil.toJsonFile("src/main/resources/initialBank.json", bankArray);

        List<BankWriter> BankList = JsonUtil.listFromJsonFile("src/main/resources/initialBank.json",BankWriter.class);
        List<SPVWriter> SPVList =  JsonUtil.listFromJsonFile("src/main/resources/initialSPV.json",SPVWriter.class); //reads lists back in to re create bank and SPVs

        Bank bankFromJSON = BankList.get(0).CreateBank(); //creates the Bank /SPVs from the Writer classes
        SPV spv1FromJSON = SPVList.get(0).CreateSPV();
        SPV spv2FromJSON = SPVList.get(1).CreateSPV();

    }

    public static void main(String[] args) throws insufficientLoansException, IOException {
        initialTest();
    }
}
