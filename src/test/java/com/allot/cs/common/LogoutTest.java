package com.allot.cs.common;

import com.allot.cs.CSBaseTest;
import com.allot.cs.page.*;
import com.allot.cs.step.LoginSteps;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;

import static com.allot.core.assertj.PageAssert.assertThat;

class LogoutTest extends CSBaseTest {

    @Autowired
    private LoginSteps loginSteps;

    @Value("${user.role}")
    private String expectedUser;

    @Feature(value = "[CS] Home page")
    @Issue("AS-14861")
    @Description("Logout test")
    @Test
    void logoutTest(SoftAssertions soft) {
        HomePage homePage = loginSteps.login();
        assertThat(homePage)
                .withFailMessage("User is not logged in!")
                .isPageDisplayed();
        String userName = homePage.getUserName();
        soft.assertThat(userName)
                .withFailMessage("User name is not as expected : {} . But expected is {}", userName, expectedUser)
                .isEqualTo(expectedUser);
        LogoutForm logoutForm = homePage.logout();
        assertThat(logoutForm)
                .withFailMessage("Logout Form is not displayed! The user is not logged out")
                .isPageDisplayed();
        soft.assertAll();
    }

}