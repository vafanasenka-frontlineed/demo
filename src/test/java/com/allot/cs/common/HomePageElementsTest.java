package com.allot.cs.common;

import com.allot.cs.CSBaseTest;
import com.allot.cs.page.HomePage;
import com.allot.cs.step.LoginSteps;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;

import static com.allot.cs.page.HomePage.HomePageElement.*;

class HomePageElementsTest extends CSBaseTest {

    @Autowired
    private LoginSteps loginSteps;

    private static final String REPORT_NAME = "Service Gateway";

    @Feature(value = "[CS] Home page")
    @Issue("AS-14860")
    @Description("Home page elements")
    @Test
    void homePageTest(SoftAssertions soft) {
        HomePage homePage = loginSteps.login();
        soft.assertThat(homePage.isElementDisplayed(LOGO))
                .withFailMessage("Logo is not displayed on the page")
                .isTrue();
        soft.assertThat(homePage.isElementDisplayed(MENU_BAR))
                .withFailMessage("Menu bar is not displayed on the page")
                .isTrue();
        soft.assertThat(homePage.getReportName())
                .as("Check that Report '%s' is displayed", REPORT_NAME)
                .contains(REPORT_NAME);
        soft.assertAll();
    }

}