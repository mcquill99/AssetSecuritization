package ASP;

import java.util.List;

public interface SpvAPI {

    void buyLoan(double interestRate, Bank bank, SPV spv);
    AssetBackedSecurity createABS(double minInterest, double maxInterest, int numLoans) throws insufficientLoansException, IllegalArgumentException;
    List<AssetBackedSecurity> getABSList();

}
