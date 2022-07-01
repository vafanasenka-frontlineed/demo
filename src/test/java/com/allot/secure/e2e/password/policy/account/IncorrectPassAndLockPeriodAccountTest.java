package com.allot.secure.e2e.password.policy.account;

import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.page.acc.*;
import com.allot.secure.e2e.password.policy.account.extension.BaseAccountManagerTest;
import com.codeborne.selenide.Selenide;
import com.epam.reportportal.annotations.attribute.*;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static com.allot.core.assertj.PageAssert.assertThat;


@Slf4j
public class IncorrectPassAndLockPeriodAccountTest extends BaseAccountManagerTest {

    private static final long LOCK_TIME_SECONDS = 120;
    private static final long MAX_INCORRECT_PASSWORD_ATTEMPTS = 2;
    private static final String INCORRECT_PASSWORD = "incorrect_pass123";

    @BeforeEach
    public void setLockTimeSecondsAndMaxIncorrectPassword() {
        sessionService.setSessionConfiguration(SessionConfigurationRequest
                .updatePasswordPolicy(LOCK_TIME_SECONDS, MAX_INCORRECT_PASSWORD_ATTEMPTS));
    }

    @Issue("AS-14584")
    @TmsLink("624")
    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Attributes(attributes = {@Attribute(key = "group", value = "Password Policy")})
    @Description("Password Policy - Incorrect Pass And lock period - Account")
    @Test
    public void incorrectPasswordAndLockPeriodTest() throws InterruptedException {
        for (int i = 1; i <= MAX_INCORRECT_PASSWORD_ATTEMPTS + 1; i++) {
            log.info("Login with incorrect password : {} attempt", i);
            accountSteps.loginToAccount(manager.getName(), INCORRECT_PASSWORD);
            assertThat(new LoginPage())
                    .withFailMessage("User is logged in with incorrect password")
                    .isPageDisplayed();
        }
        log.info("Wait for a half of the lock time for incorrect password input");
        long halfOfLockTimeMillis = LOCK_TIME_SECONDS / 2 * 1000;
        Selenide.sleep(halfOfLockTimeMillis);
        log.info("Check that user is not able to login before lock time ended");
        accountSteps.loginToAccount(manager);
        assertThat(new LoginPage())
                .withFailMessage("User is logged in before the lock time ended")
                .isPageDisplayed();
        log.info("Wait for lock time ended for incorrect password input");
        Selenide.sleep(halfOfLockTimeMillis);
        HomePage homePage = accountSteps.loginToAccount(manager);
        assertThat(homePage)
                .withFailMessage("User is not logged in after lock time ended")
                .isPageDisplayed();
    }

}
