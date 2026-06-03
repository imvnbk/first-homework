import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase{

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");

// Заполнение полей в таблице

        $("[id=firstName]").setValue("Name");
        $("[id=lastName]").setValue("Surname");
        $("[id=userEmail]").setValue("useremail@test.com");
        $("[id=gender-radio-1]").click();
        $("[id=userNumber]").setValue("7075919711");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select")
                .selectOption("September");
        $(".react-datepicker__year-select")
                .selectOption("1997");
        $(".react-datepicker__day--011")
                .click();
        $("#subjectsInput")
                .setValue("Math")
                .pressEnter();
        $("[id=hobbies-checkbox-1]").click();
        $("#uploadPicture").uploadFromClasspath("img/test.jpg");
        $("[id=currentAddress]").setValue("Test address 211");
        $("#state").scrollTo().shouldBe(Condition.visible).click();
        $("#state")
                .find(byText("NCR"))
                .shouldBe(Condition.visible)
                .click();
        $("#state").click();
        $("#city").click();
        $("#city").find(byText("Delhi")).click();

//Нажать кнопку Submit
        $("#submit").click();

//Проверка заполненных полей
        $(".table-responsive").shouldHave(text("Name Surname"));
        $(".table-responsive").shouldHave(text("useremail@test.com"));
        $(".table-responsive").shouldHave(text("7075919711"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("11 September,1997"));
        $(".table-responsive").shouldHave(text("test.jpg"));
        $(".table-responsive").shouldHave(text("Math"));
        $(".table-responsive").shouldHave(text("NCR"));
        $(".table-responsive").shouldHave(text("Delhi"));
    }

    @Test
    void onlyRequiredFieldsFillTest(){
        open("/automation-practice-form");

        // Заполнение полей в таблице

        $("[id=firstName]").setValue("Name");
        $("[id=lastName]").setValue("Surname");
        $("[id=gender-radio-1]").click();
        $("[id=userNumber]").setValue("7075919711");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select")
                .selectOption("September");
        $(".react-datepicker__year-select")
                .selectOption("1997");
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