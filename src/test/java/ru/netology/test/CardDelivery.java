package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.netology.data.UserInfo;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static ru.netology.generator.DataGenerator.generateDate;
import static ru.netology.generator.DataGenerator.generateUser;

public class CardDelivery {
    SelenideElement form = $x("//form");
    SelenideElement success = $x("//div[@data-test-id='success-notification']");
    SelenideElement replan = $x("//div[@data-test-id='replan-notification']");
    SelenideElement error = $x("//div[@data-test-id='error-notification']");

    @BeforeMethod
    public void setup() {
        open("http://localhost:9999/");
    }

    @Test
    public void happyPath() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);
    }

    @Test
    public void happyPathReplanOne() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);

        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").
                val(generateDate(4));
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        replan.should(visible, ofSeconds(15));
        replan.$x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        replan.should(hidden);
        success.should(visible);
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                generateDate(4)));
        success.$x(".//button").click();
        success.should(hidden);
    }

    @Test
    public void happyPathReplanTwo() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);

        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        replan.should(visible, ofSeconds(15));
        replan.$x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        replan.should(hidden);
        success.should(visible);
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);
    }

    @Test
    public void happyPathReplanThree() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);

        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").
                val(generateDate(6));
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        replan.should(visible, ofSeconds(15));
        replan.$x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        replan.should(hidden);
        success.should(visible);
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                generateDate(6)));
        success.$x(".//button").click();
        success.should(hidden);
    }

    @Test
    public void sadPathReplan() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно запланирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);

        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").
                val(generateDate(2));
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();

        form.$x(".//span[@data-test-id='date']//child::span[@class='input__sub']").
                should(visible, text("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void emptyCityTest() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();
        form.$x(".//span[@data-test-id='city']").should(cssClass("input_invalid"));
        form.$x(".//span[@data-test-id='city']//child::span[@class='input__sub']").
                should(visible, text("Поле обязательно для заполнения"));
    }

    @Test
    public void emptyNameTest() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();
        form.$x(".//span[@data-test-id='name']").should(cssClass("input_invalid"));
        form.$x(".//span[@data-test-id='name']//child::span[@class='input__sub']").
                should(visible, text("Поле обязательно для заполнения"));
    }

    @Test
    public void emptyPhoneTest() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//label[@data-test-id='agreement']").click();
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();
        form.$x(".//span[@data-test-id='phone']").should(cssClass("input_invalid"));
        form.$x(".//span[@data-test-id='phone']//child::span[@class='input__sub']").
                should(visible, text("Поле обязательно для заполнения"));
    }

    @Test
    public void emptyCheckboxTest() {
        UserInfo user = generateUser("ru", 5);
        form.$x(".//span[@data-test-id='city']//child::input").val(user.getCity());
        form.$x(".//span[@data-test-id='date']//child::input[@class='input__control']").val(user.getDate());
        form.$x(".//span[@data-test-id='name']//child::input").val(user.getName());
        form.$x(".//span[@data-test-id='phone']//child::input").val(user.getPhone());
        form.$x(".//button//ancestor::span[contains(text(), 'Запланировать')]").click();
        form.$x(".//label[@data-test-id='agreement']").should(cssClass("input_invalid"));
    }
}