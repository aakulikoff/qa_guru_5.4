package pageObjects.scenarios;

import org.junit.jupiter.api.Test;

public class RegistartionTests {

    RegistrationPage RegistrationPage = new RegistrationPage();


    @Test
    void successRegistartionTest() {
        RegistrationPage.openPage();
        RegistrationPage.fillFrom();
        RegistrationPage.checkData();

    }
}
