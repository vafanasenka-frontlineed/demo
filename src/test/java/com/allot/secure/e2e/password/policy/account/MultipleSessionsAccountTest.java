package com.allot.secure.e2e.password.policy.account;

import com.allot.core.assertj.PageAssert;
import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.page.acc.HomePage;
import com.allot.secure.e2e.password.policy.account.extension.BaseAccountManagerTest;
import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.allot.core.assertj.PageAssert.assertThat;


public class MultipleSessionsAccountTest extends BaseAccountManagerTest {

    private static final long MAX_SESSIONS = 2;
    private List<WebDriver> driverList = new ArrayList<>();

    @BeforeEach
    public void updateSessionConfiguration() {
        sessionService.setSessionConfiguration(SessionConfigurationRequest.updateMaxSessions(MAX_SESSIONS));
    }

    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Issue("AS-14375")
    @TmsLink("620")
    @Description("[C620] Password Policy - Multiple sesions - Account  ")
    @Test
    public void multipleSessionsTest() {
        accountSteps.loginToAccount(manager);
        driverList.add(loginInNewBrowserSession(PageAssert::isPageDisplayed,
                "User is not logged in with the 1st session"));
        driverList.add(loginInNewBrowserSession(PageAssert::isPageDisplayed,
                "User is not logged in with the 2nd session"));
        driverList.add(loginInNewBrowserSession(PageAssert::isPageNotDisplayed,
                "User logged in with the 3d session. Only 2 sessions are allowed!"));
    }

    private WebDriver loginInNewBrowserSession(UnaryOperator<PageAssert> assertFunction, String faiMessage) {
        WebDriver driver = new SelenideDriver(new SelenideConfig()).getAndCheckWebDriver();
        WebDriverRunner.setWebDriver(driver);
        HomePage homePage = accountSteps.loginToAccount(manager);
        assertFunction.apply(assertThat(homePage)
                .withFailMessage(faiMessage));
        return driver;
    }

    @AfterAll
    public void closeDrivers() {
        for (WebDriver driver : driverList) {
            WebDriverRunner.setWebDriver(driver);
            WebDriverRunner.closeWebDriver();
        }
    }

}
