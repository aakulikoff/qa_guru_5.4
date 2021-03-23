package pageObjects.steps;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private SelenideElement pageHeader = $(".main-header");
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement userNumberInput = $("#userNumber");


    public void checkPageHeader(String headerText) {
        pageHeader.shouldHave(text(headerText));
    }

    public void setFirstName(String nameValue) {
        firstNameInput.val(nameValue);
    }

    public void setLastNameInput(String lastValue) {
        lastNameInput.val(lastValue);
    }

    public void setUserEmailInput(String emailValue) {
        userEmailInput.val(emailValue);
    }

    public void setUserNumberInput(String mobileValue) {
        userNumberInput.val(mobileValue);
    }


}


