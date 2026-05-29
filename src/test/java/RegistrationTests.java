import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase{

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");
// Ввод имени
        $("[id=firstName]").setValue("Name");

// Ввод фамилии
        $("[id=lastName]").setValue("Surname");

// Ввод email
        $("[id=userEmail]").setValue("useremail@test.com");

// Выбор пола
        $("[id=gender-radio-1]").click();

// Ввод номера телефона
        $("[id=userNumber]").setValue("7075919711");

// --------------------
// Выбор даты рождения
// --------------------
// Открытие календаря
        $("[id=dateOfBirthInput]").click();
// Выбор месяца
        $(".react-datepicker__month-select")
                .selectOption("September");
// Выбор года
        $(".react-datepicker__year-select")
                .selectOption("1997");
// Выбор дня
        $(".react-datepicker__day--011")
                .click();

//Выбор предмета
        $("#subjectsInput")
                .setValue("Math")
                .pressEnter();

// Выбор хобби
        $("[id=hobbies-checkbox-1]").click();

//Загрузка картинки
        File file = new File("/Users/koyshikenmail.ru/Desktop/42dd34709012230a11aedf23987dde57.jpg");

        $("#uploadPicture").uploadFile(file);

        $("[id=currentAddress]").setValue("Test address 211");

//Выбор штата
        $("#state").scrollTo().shouldBe(Condition.visible).click();

        $("#state")
                .find(byText("NCR"))
                .shouldBe(Condition.visible)
                .click();
        $("#state").click();

//Выбор города
        $("#city").click();
        $("#city").find(byText("Delhi")).click();

//Кнопка Submit
        $("#submit").click();

//Проверка заполненных полей
        $(".table-responsive").shouldHave(text("Name Surname"));
        $(".table-responsive").shouldHave(text("useremail@test.com"));
        $(".table-responsive").shouldHave(text("7075919711"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("11 September,1997"));
        $(".table-responsive").shouldHave(text("Math"));
        $(".table-responsive").shouldHave(text("NCR"));
        $(".table-responsive").shouldHave(text("Delhi"));
    }

    @Test
    void onlyRequiredFieldsFillTest(){
        open("/automation-practice-form");
        // Ввод имени
        $("[id=firstName]").setValue("Name");

        // Ввод фамилии
        $("[id=lastName]").setValue("Surname");

        // Выбор пола
        $("[id=gender-radio-1]").click();

        // Ввод номера телефона
        $("[id=userNumber]").setValue("7075919711");
        // --------------------
        // Выбор даты рождения
        // --------------------
        // Открытие календаря
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца
        $(".react-datepicker__month-select")
                .selectOption("September");
        // Выбор года
        $(".react-datepicker__year-select")
                .selectOption("1997");
        // Выбор дня
        $(".react-datepicker__day--011")
                .click();

        //Кнопка Submit
        $("#submit").scrollTo().click();

        //Проверка заполненных полей
        $(".table-responsive").shouldHave(text("Name Surname"));
        $(".table-responsive").shouldHave(text("7075919711"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("11 September,1997"));
    }

    @Test
    void firstNameValidationTest() {

        open("/automation-practice-form");

        // Нажимаем Submit без заполнения формы
        $("#submit").scrollTo().click();

        // Проверяем что поля валидируются
        $("#userForm").shouldHave(cssClass("was-validated"));

        // Проверяем, что поле FirstName подсветилось красным
        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        // Проверяем, что модальное окно НЕ появилось
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
    }

    @Test
    void lastNameValidationTest() {
        open("/automation-practice-form");

        // Нажимаем Submit без заполнения формы
        $("#submit").scrollTo().click();

        // Проверяем что поля валидируются
        $("#userForm").shouldHave(cssClass("was-validated"));

        // Проверяем, что поле LastName подсветилось красным
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        // Проверяем, что модальное окно НЕ появилось
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);    }

    @Test
    void userNumberValidationTest() {
        open("/automation-practice-form");

        // Нажимаем Submit без заполнения формы
        $("#submit").scrollTo().click();

        // Проверяем что поля валидируются
        $("#userForm").shouldHave(cssClass("was-validated"));

        // Проверяем, что поле Mobile Number подсветилось красным
        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        // Проверяем, что модальное окно НЕ появилось
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
    }
}