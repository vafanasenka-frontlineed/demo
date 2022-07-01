package com.allot.secure.e2e.password.policy.account;

import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.page.acc.HomePage;
import com.allot.secure.e2e.password.policy.account.extension.BaseAccountManagerTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.allot.core.assertj.PageAssert.assertThat;


public class ExpirationTimeAccountTest extends BaseAccountManagerTest {

    private static final long SESSION_EXPIRATION_TIME_MINUTES = 1;

    @BeforeEach
    public void setSessionExpirationTimeMinutes() {
        sessionService.setSessionConfiguration(SessionConfigurationRequest
                .updateRefreshTokenTimeout(SESSION_EXPIRATION_TIME_MINUTES));
    }

    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Issue("AS-14028")
    @TmsLink("622")
    @Description("[C622] Password Policy - Expiration time - Account ")
    @Test
    public void expirationTimeTest() {
        HomePage homePage = accountSteps.loginToAccount(manager);
        Selenide.sleep(SESSION_EXPIRATION_TIME_MINUTES * 60000);
        assertThat(homePage)
                .withFailMessage("Session is not expired in %s minute[s]", SESSION_EXPIRATION_TIME_MINUTES)
                .isPageNotDisplayed();
    }

}
