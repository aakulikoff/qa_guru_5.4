
package lesson_4_pageObjects_randomUnits_javaFaker;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

public class PracticeFormWithRandomUtilsTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }
//    открытие браузера в полном окне

    String firstName = getRandomString(5),
            lastName = getRandomString(10),
            userEmail = getRandomEmail(),
            userNumber = getRandomPhone(),
            monthOfBirth = "August",
            yearOfBirth = "1987",
            gender = "Male",
            dayOfBirth = "2",
            subject = "Arts",
            hobby = "Sports",
            currentAddress = getRandomMessage(5,9),
            picture = "pic.jpeg",
            state = "NCR",
            city = "Delhi";

    @Test
    void fillFormTest() {

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $("[aria-label='Choose Sunday, August 2nd, 1987']").click();

        $("#subjectsInput").val(subject).pressEnter();
        $(byText(hobby)).click();

        $("#uploadPicture").uploadFromClasspath("img/" + picture);

        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $(byText(state)).click();
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
