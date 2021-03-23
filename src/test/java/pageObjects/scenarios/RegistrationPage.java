package pageObjects.scenarios;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

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
    // TODO:  временно

    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
    }

    public void fillFrom() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        setData(yearOfBirth, monthOfBirth, dayOfBirth, dayOfWeekOfBirth);
        $("#subjectsInput").val(subject).pressEnter();

        $("#hobbiesWrapper").$(byText(hobby)).click();

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

    }

    public void setData(String year, String month, String day, String dayOfWeek) {

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
                dayOfWeek, month, day, year)).click();

    }


    public void checkData() {
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
