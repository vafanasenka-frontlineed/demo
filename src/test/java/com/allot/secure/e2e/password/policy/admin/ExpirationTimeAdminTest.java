package com.allot.secure.e2e.password.policy.admin;

import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.page.isp.LoginPage;
import com.allot.secure.e2e.password.policy.admin.extension.BaseAdministratorTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.allot.core.assertj.PageAssert.assertThat;


public class ExpirationTimeAdminTest extends BaseAdministratorTest {

    private static final long SESSION_EXPIRATION_TIME_MINUTES = 1;

    @BeforeEach
    public void setSessionExpirationTimeMinutes() {
        sessionService.setSessionConfiguration(SessionConfigurationRequest
                .updateRefreshTokenTimeout(SESSION_EXPIRATION_TIME_MINUTES));
    }

    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Issue("AS-14027")
    @TmsLink("621")
    @Description("[C621] Password Policy - Expiration time - Administrator")
    @Test
    public void expirationTimeTest() {
        adminUiSteps.login(administrator);
        Selenide.sleep(SESSION_EXPIRATION_TIME_MINUTES * 60000);
        assertThat(new LoginPage())
                .withFailMessage("Session is not expired in %s minute[s]", SESSION_EXPIRATION_TIME_MINUTES)
                .isPageDisplayed();
    }

}
