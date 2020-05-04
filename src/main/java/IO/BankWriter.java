package IO;

import ASP.Bank;
import ASP.Borrower;
import ASP.Loan;
import ASP.SPV;
import com.fasterxml.jackson.annotation.JsonIgnore;
import util.KeyValueContainer;
import util.ObjectUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankWriter {
    private Borrower[] customers;
    private Loan[]loans;
    private SPV[] spvs;
    private double balance;

    public BankWriter(){

    }
    public BankWriter(Bank bank){
        customers = bank.getCustomers().values().toArray(new Borrower[0]);
        loans = bank.getLoans().values().toArray(new Loan[0]);
        spvs = bank.getSPVLoanPairs().values().toArray(new SPV[0]);
        balance = bank.getBalance();
    }
    public Bank CreateBank(){
        Bank bank = new Bank(balance);
        bank.setSPVLoanPairs(generateSPVLoanPairsMap());
        bank.setBorrowerLoanPairs(generateBorrowerLoanPairsMap());
        bank.setLoans(generateLoanMap());
        bank.setCustomers(generateCustomerMap());

        return bank;
    }

    public Map<Integer,Borrower> generateCustomerMap(){
        Map<Integer,Borrower> toReturn = new HashMap<>();
        for(Borrower customer : customers){
            toReturn.put(customer.getId(),customer);
        }
        return toReturn;

    }
    public Map<Integer,SPV> generateSPVMap(){
        Map<Integer,SPV> toReturn = new HashMap<>();
        for(SPV spv : spvs){
            toReturn.put(spv.getId(),spv);
        }
        return toReturn;

    }
    public Map<Integer,Loan> generateLoanMap(){
        Map<Integer, Loan> toReturn = new HashMap<>();
        for(Loan loan : loans){
            toReturn.put(loan.getId(), loan);
        }
        return toReturn;
    }

    public Map<Loan,Borrower> generateBorrowerLoanPairsMap(){
        Map<Integer,Borrower> borrowers = generateCustomerMap();
        Map<Loan,Borrower> toReturn = new HashMap<>();
        for(Loan loan : loans){
            if(loan.getBorrowerId() != 0){
                Borrower toAdd = borrowers.get(loan.getBorrowerId());
                toReturn.put(loan, toAdd);
            }

        }
        return toReturn;
    }
    public Map<Integer,SPV> generateSPVLoanPairsMap(){
        Map<Integer,SPV> toReturn = new HashMap<>();
        Map<Integer,SPV> spvMap = generateSPVMap();
        for(Loan loan : loans){
            if(loan.getSPVId() != 0){
                SPV spv = spvMap.get(loan.getSPVId());
                toReturn.put(loan.getId(), spv);
            }
        }
        return toReturn;
    }


    public void setCustomers(Borrower[] customers){
        this.customers = customers;
    }
   public void setLoans(Loan[] loans){
        this.loans = loans;
    }
    public void setSPVs(SPV[] SPVs){
        this.spvs = SPVs;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Borrower[] getCustomers(){
        return customers;
    }
    public Loan[] getLoans(){
        return loans;
    }

    public SPV[] getSPVs(){
        return spvs;
    }
    public double getBalance() {
        return  balance;
    }
}
