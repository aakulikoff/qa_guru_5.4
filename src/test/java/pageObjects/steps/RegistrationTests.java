package pageObjects.steps;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {

    RegistrationPage RegistrationPage = new RegistrationPage();

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }
//    открытие браузера в полном окне

    String firstName = "Peter",
            lastName = "The Great",
            userEmail = "russia@rf.ru",
            userNumber = "9119991122",
            monthOfBirth = "August",
            yearOfBirth = "1987",
            gender = "Male",
            dayOfBirth = "2",
            dayOfWeekOfBirth = "Sunday",
            subject = "Arts",
            hobby = "Sports",
            currentAddress = "Russia, Saint-Petersburg, Palace",
            picture = "pic.jpeg",
            state = "NCR",
            city = "Delhi";

    @Test
    void fillFormTest() {

        open("https://demoqa.com/automation-practice-form");

        RegistrationPage.checkPageHeader("Practice Form");
//        $(".main-header").shouldHave(text("Practice Form"));

        RegistrationPage.setFirstName(firstName);
//        $("#firstName").setValue(firstName);

        RegistrationPage.setLastNameInput(lastName);
//        $("#lastName").setValue(lastName);

        RegistrationPage.setUserEmailInput(userEmail);
//        $("#userEmail").setValue(userEmail);

        $(byText(gender)).click();

        RegistrationPage.setUserNumberInput(userNumber);
//        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
                dayOfWeekOfBirth, monthOfBirth, dayOfBirth, yearOfBirth)).click();

        $("#subjectsInput").val(subject).pressEnter();
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName),
                text(userEmail), text(gender));

//        Проверка данных во всплывающем окне
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(userEmail));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(userNumber));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));

    }

}
