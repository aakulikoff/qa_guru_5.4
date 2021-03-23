package lesson_4_pageObjects_randomUnits_javaFaker;

import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.stream.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utils.FileUtils.readStringFromFile;

public class PracticeFormWithElementsCollectionTests {


    static String firstName = "Peter",
            lastName = "The Great",
            userEmail = "russia@rf.ru",
            userNumber = "9119991122",
            monthOfBirth = "August",
            yearOfBirth = "1987",
            gender = "Male",
            dayOfBirth = "2",
            subject = "Arts",
            hobby = "Sports",
            currentAddress = "Russia, Saint-Petersburg, Palace",
            picture = "pic.jpeg",
            state = "NCR",
            city = "Delhi";

    static Map<String, String> expectedData = new HashMap<String, String>() {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", userEmail);
        put("Gender", gender);
        put("Mobile", userNumber);
        put("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        put("Subjects", subject);
    }};

    @Test
    void successfulFillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").val(userNumber);
        // set date
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();
        // set subject
        $("#subjectsInput").val(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();

        // set hobbies
        $("#hobbiesWrapper").$(byText(hobby)).click();
        // upload image
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        // set current address
        $("#currentAddress").val(currentAddress);
        // set state and city
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        // asserts
        SoftAssertions softly = new SoftAssertions();
        for (SelenideElement element : $$(".table-responsive tbody tr")) {
            String key = element.$("td").getText();
            String actualValue = element.$("td", 1).getText();
            String expectedValue = expectedData.get(key);

            softly.assertThat(actualValue).isEqualTo(expectedValue);
        }
        softly.assertAll();


    }

}