package com.allot.cs.common;

import com.allot.cs.CSBaseTest;
import com.allot.cs.page.*;
import com.allot.cs.step.LoginSteps;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;

import static com.allot.core.assertj.PageAssert.assertThat;
import static com.allot.util.HttpUrlBuilder.HTTPSchema.*;

class LoginTest extends CSBaseTest {

    @Autowired
    private LoginSteps loginSteps;

    @Value("${app.user}")
    private String userName;

    private static final String INCORRECT_PASSWORD = "incorrect123#";

    @Feature(value = "[CS] Login")
    @Issue("AS-14853")
    @Description("Login with correct data")
    @Test
    void loginTest() {
        HomePage homePage = loginSteps.login();
        assertThat(homePage)
                .withFailMessage("User is not logged in with correct data and HTTP protocol!")
                .isPageDisplayed();
    }

    @Feature(value = "[CS] Login")
    @Issue("AS-16314")
    @Description("Clear See login with HTTPS")
    @Test
    void loginHTTPSTest() {
        HomePage homePage = loginSteps.login(HTTPS);
        assertThat(homePage)
                .withFailMessage("User is not logged in with correct data and HTTPS protocol!")
                .isPageDisplayed();
    }

    @Feature(value = "[CS] Login")
    @Issue("AS-14854")
    @Description("Login with incorrect password")
    @Test
    void loginWithIncorrectPasswordTest() {
        HomePage homePage = Selenide.open(loginSteps.getBaseUrl(HTTP), LoginPage.class)
                .login(userName, INCORRECT_PASSWORD);
        assertThat(homePage)
                .withFailMessage("User is logged in with incorrect password!")
                .isPageNotDisplayed();
        Assertions.assertThat(new LoginPage().isWrongPasswordMessageDisplayed())
                .withFailMessage("Wrong password message is not displayed")
                .isTrue();
    }

}