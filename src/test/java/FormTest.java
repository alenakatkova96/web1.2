import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {
    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Матвей");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNegativeName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Sergey Матвей");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void notName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNegativePhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Матвей");
        $("[data-test-id=phone] input").setValue("+792700");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Матвей");

        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNegativeAdreement() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иванов Матвей");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
