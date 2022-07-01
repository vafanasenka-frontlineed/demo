package com.allot.cs.step;

import com.allot.cs.page.*;
import com.allot.cs.page.password.ChangePasswordPage;
import com.allot.util.HttpUrlBuilder;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.allot.util.HttpUrlBuilder.HTTPSchema;
import static com.allot.util.HttpUrlBuilder.HTTPSchema.HTTP;


@Slf4j
@Component
@Getter
public class LoginSteps {

    @Value("${app.host}")
    private String host;

    @Value("${app.path}")
    private String path;

    @Value("${app.user}")
    private String defaultUserName;

    @Value("${user.default.password}")
    private String defaultUserPassword;

    @Value("${user.password}")
    private String userPassword;

    @Step("Login to Clear See with the user {user}")
    public HomePage login(@NonNull final String userName, @NonNull final String password, HTTPSchema schema) {
        String baseUrl = getBaseUrl(schema);
        log.info("Open Clear see by url {} ", baseUrl);
        LoginPage loginPage = Selenide.open(baseUrl, LoginPage.class);
        HomePage homePage = loginPage.login(userName, password);
        if (loginPage.isPresent() && loginPage.isWrongPasswordMessageDisplayed()) {
            changeDefaultPassword(password);
        }
        homePage.waitUntilPageLoaded();
        return homePage;
    }

    public HomePage login(@NonNull final String userName, String password) {
        return login(userName, password, HTTP);
    }

    public HomePage login(@NonNull final HTTPSchema schema) {
        return login(defaultUserName, userPassword, schema);
    }

    public HomePage login() {
        return login(HTTP);
    }

    public String getBaseUrl(HTTPSchema schema) {
        return HttpUrlBuilder.build(schema, host, schema.equals(HTTP) ? 8080 : 8443, path);
    }

    private void changeDefaultPassword(@NonNull final String password) {
        log.info("Change default password for '{}' with first login ", defaultUserName);
        Selenide.page(LoginPage.class).login(defaultUserName, defaultUserPassword);
        var changePasswordPage = Selenide.page(ChangePasswordPage.class);
        if (changePasswordPage.isPresent()) {
            changePasswordPage.updatePassword(defaultUserName, password);
        }
    }

}
