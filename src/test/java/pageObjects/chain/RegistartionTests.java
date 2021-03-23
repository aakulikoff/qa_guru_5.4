package pageObjects.chain;

import org.junit.jupiter.api.*;

public class RegistartionTests {

    pageObjects.chain.RegistrationPage RegistrationPage = new RegistrationPage();


    @Test
    void successRegistartionTest() {
        RegistrationPage
                .openPage()
                .fillFrom()
                .checkData();

    }
}
