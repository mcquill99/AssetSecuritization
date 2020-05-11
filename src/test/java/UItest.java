import ASP.*;

import java.io.IOException;

public class UItest {

    public static void main(String[] args) throws IOException {
        SpvAPI testAPI = new SPV();
        InvestorAPI investorTestAPI = new Investor();
        UI testUI = new UI(testAPI, investorTestAPI);
        testUI.loginPage();

    }
}
