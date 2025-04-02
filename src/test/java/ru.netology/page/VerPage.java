package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class VerPage {
    private final SelenideElement head = $(".heading");
    private final SelenideElement code = $("[data-test-id=code] input");
    private final SelenideElement button = $("[data-test-id=action-verify] .button__content");
    private final SelenideElement massageError = $("[data-test-id=error-notification] .notification__content");

    public VerPage() {
        head.shouldBe(Condition.visible).shouldHave(Condition.text("Интернет Банк"));
        code.shouldBe(Condition.visible);
    }

    public DashboardPage validVer(String verificationCode) {
        notValidVer(verificationCode);
        return new DashboardPage();
    }

    public void notValidVer(String verificationCode) {
        code.setValue(verificationCode);
        button.click();
    }

    public void massageError(String massage) {
        massageError.shouldHave(exactText(massage)).shouldBe(Condition.visible);
    }
}
