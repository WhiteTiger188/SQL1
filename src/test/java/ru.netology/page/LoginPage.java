package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement button = $("[data-test-id=action-login] .button__content");
    private final SelenideElement massageError = $("[data-test-id=error-notification] .notification__content");

    public VerPage validLogin(DataHelper.LoginInfo loginInfo) {
        noValidlogin(loginInfo);
        return new VerPage();
    }

    public void noValidlogin(DataHelper.LoginInfo loginInfo) {
        loginField.setValue(loginInfo.getLogin());
        passwordField.setValue(loginInfo.getPassword());
        button.click();
    }

    public void massageError(String massage) {
        massageError.shouldHave(exactText(massage)).shouldBe(Condition.visible);
    }
}
