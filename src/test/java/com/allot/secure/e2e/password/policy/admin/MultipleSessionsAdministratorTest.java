package com.allot.secure.e2e.password.policy.admin;

import com.allot.core.assertj.PageAssert;
import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.page.isp.AccountsPage;
import com.allot.secure.e2e.password.policy.admin.extension.BaseAdministratorTest;
import com.codeborne.selenide.*;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import java.util.*;
import java.util.function.UnaryOperator;

import static com.allot.core.assertj.PageAssert.assertThat;


@Slf4j
public class MultipleSessionsAdministratorTest extends BaseAdministratorTest {

    private static final long MAX_SESSIONS = 2;
    private List<WebDriver> driverList = new ArrayList<>();

    @BeforeEach
    public void setMaxSessions() {
        sessionService.setSessionConfiguration(SessionConfigurationRequest.updateMaxSessions(MAX_SESSIONS));
    }

    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Issue("AS-14025")
    @TmsLink("328")
    @Description("[C328] Password Policy Multiple sessions - Administrator")
    @Test
    public void multipleSessionsTest() {
        // TODO refactor with WedDriverProvider
        driverList.add(loginInNewBrowserSession(PageAssert::isPageDisplayed,
                "User is not logged in with the 1st session"));
        driverList.add(loginInNewBrowserSession(PageAssert::isPageDisplayed,
                "User is not logged in with the 2nd session"));
        driverList.add(loginInNewBrowserSession(PageAssert::isPageNotDisplayed,
                "User logged in with the 3d session. Only 2 sessions are allowed!"));
    }

    private WebDriver loginInNewBrowserSession(UnaryOperator<PageAssert> assertFunction, String faiMessage) {
        log.info("Login in a new session");
        WebDriver driver = new SelenideDriver(new SelenideConfig()).getAndCheckWebDriver();
        WebDriverRunner.setWebDriver(driver);
        AccountsPage accountsPage = adminUiSteps.login(administrator);
        assertFunction.apply(assertThat(accountsPage)
                .withFailMessage(faiMessage));
        return driver;
    }

    @AfterEach
    public void closeDrivers() {
        for (WebDriver driver : driverList) {
            WebDriverRunner.setWebDriver(driver);
            WebDriverRunner.closeWebDriver();
        }
    }

}
