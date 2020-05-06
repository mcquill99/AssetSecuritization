import ASP.*;

import java.io.IOException;

public class UItest {

    public static void main(String[] args) throws IOException {
        SpvAPI testAPI = new SPV();
        UI testUI = new UI(testAPI);
        testUI.loginPage();

    }
}
