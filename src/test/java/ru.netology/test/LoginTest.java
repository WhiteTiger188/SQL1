package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.getRandomUser;
import static ru.netology.data.DataHelper.getRandomVerCode;
import static ru.netology.data.SQLHelper.*;


public class LoginTest {
    LoginPage loginPage;
    DataHelper.LoginInfo loginInfo = DataHelper.getTestUser();

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterEach
    void clear() {
        clearAuthCodes();
    }

    @AfterAll
    static void clearAll() {
        clearDataBase();
    }

    @Test
    void successLoginWithTestData() {
        var verPage = loginPage.validLogin(loginInfo);
        verPage.validVer(getVerCode());
    }

    @Test
    void negativeLoginWithRandomData() {
        loginPage.noValidlogin(getRandomUser());
        loginPage.massageError("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void negativeWithInvalidVerCode() {
        var verPage = loginPage.validLogin(loginInfo);
        verPage.notValidVer(getRandomVerCode().getCode());
        verPage.massageError("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }
}
