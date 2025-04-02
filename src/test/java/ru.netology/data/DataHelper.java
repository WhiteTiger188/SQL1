package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

    private static String getRandomLogin() {
        return faker.name().username();
    }

    private static String getRandomPass() {
        return faker.internet().password();
    }

    public static LoginInfo getRandomUser() {
        return new LoginInfo(getRandomLogin(), getRandomPass());
    }

    public static LoginInfo getTestUser() {
        return new LoginInfo("vasya", "qwerty123");
    }

    public static VerificationCode getRandomVerCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class LoginInfo {
        String login;
        String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }
}