package com.allot.secure.e2e.password.policy.admin;

import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.page.isp.*;
import com.allot.secure.e2e.password.policy.admin.extension.BaseAdministratorTest;
import com.codeborne.selenide.Selenide;
import com.epam.reportportal.annotations.attribute.*;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static com.allot.core.assertj.PageAssert.assertThat;


@Slf4j
public class IncorrectPassAndLockPeriodAdminTest extends BaseAdministratorTest {

    private static final long LOCK_TIME_SECONDS = 60;
    private static final long MAX_INCORRECT_PASSWORD_ATTEMPTS = 2;
    private static final String INCORRECT_PASSWORD = "incorrect_pass123";

    @BeforeEach
    public void setLockTimeSecondsAndMaxIncorrectPassword() {
        sessionService.setSessionConfiguration(SessionConfigurationRequest
                .updatePasswordPolicy(LOCK_TIME_SECONDS, MAX_INCORRECT_PASSWORD_ATTEMPTS));
    }

    @Issue("AS-14411")
    @TmsLink("623")
    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Attributes(attributes = {@Attribute(key = "group", value = "Password Policy")})
    @Description("Password Policy - Incorrect Pass And lock period - Administrator")
    @Test
    public void incorrectPasswordAndLockPeriodTest() throws InterruptedException {
        for (int i = 1; i <= MAX_INCORRECT_PASSWORD_ATTEMPTS + 1; i++) {
            log.info("Login with incorrect password : {} attempt", i);
            adminUiSteps.login(administrator.getName(), INCORRECT_PASSWORD);
            assertThat(new LoginPage())
                    .withFailMessage("User is logged in with incorrect password")
                    .isPageDisplayed();
        }
        log.info("Wait for a half of the lock time for incorrect password input");
        long halfOfLockTimeMillis = LOCK_TIME_SECONDS / 2 * 1000;
        Selenide.sleep(halfOfLockTimeMillis);
        log.info("Check that user is not able to login before lock time ended");
        adminUiSteps.login(administrator);
        assertThat(new LoginPage())
                .withFailMessage("User is logged in before the lock time ended")
                .isPageDisplayed();
        log.info("Wait for lock time ended for incorrect password input");
        Selenide.sleep(halfOfLockTimeMillis);
        AccountsPage accountsPage = adminUiSteps.login(administrator);
        assertThat(accountsPage)
                .withFailMessage("User is not logged in after lock time ended")
                .isPageDisplayed();
    }

}
