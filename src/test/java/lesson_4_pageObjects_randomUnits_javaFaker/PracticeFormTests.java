package lesson_4_pageObjects_randomUnits_javaFaker;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

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
        $(".main-header").shouldHave(text("Practice Form"));
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

//        выбор даты рождения из появившейся формы #1:
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $("[aria-label='Choose Sunday, August 2nd, 1987']").click();
//        выбор даты рождения из появившейся формы #2:
//        $("#dateOfBirthInput").click();
//        $(".react-datepicker__month-select").selectOption(monthOfBirth);
//        $(".react-datepicker__year-select").selectOption(yearOfBirth);
//        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
//        dayOfWeekOfBirth, monthOfBirth, dayOfBirth, yearOfBirth)).click();
//        выбор даты рождения из появившейся формы #3:
//        $(by("aria-label", "Choose Tuesday, May 10th, 1988")).click();
//        $$(String.format(".react-datepicker__day--0%s", dayOfBirth))
//                .filter(not(cssClass("react-datepicker__day--outside-month")))
//                .first().click();
//        выбор даты рождения из появившейся формы #4:
//        $$(".react-datepicker__day--0" + dayOfBirth)
//                .filter(not(cssClass("react-datepicker__day--outside-month")))
//                .first().click();
//        $(String.format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", dayOfBirth)).click();
//        $(".react-datepicker__day--0" + dayOfBirth + ":not(.react-datepicker__day--outside-month)").click();

//        выборор нужного параметра из всплывающих подсказок №1:
        $("#subjectsInput").val(subject).pressEnter();
//        выборор нужного параметра из всплывающих подсказок №2:
//        $("#subjectsInput").val(subjectsInput);
//        $(".subjects-auto-complete__menu-list").$(byText(subjectsInput)).click(); или $(byText(subjectsInput)).click()

        $("#hobbiesWrapper").$(byText(hobby)).click();
//        $(byText(hobby)).click();

//        загрузка фото из файла #1:
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
//        загрузка фото из файла #2:
//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/" + picture));

        $("#currentAddress").val(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
//        $(byText(state)).click();
        $("#city").click();
//        $(byText(city)).click();
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
