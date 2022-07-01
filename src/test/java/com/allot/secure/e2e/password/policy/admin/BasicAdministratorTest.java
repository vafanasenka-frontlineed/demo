package com.allot.secure.e2e.password.policy.admin;

import com.allot.domain.asm.frontend.page.isp.AccountsPage;
import com.allot.secure.e2e.password.policy.admin.extension.BaseAdministratorTest;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.allot.core.assertj.PageAssert.assertThat;


@Slf4j
public class BasicAdministratorTest extends BaseAdministratorTest {


    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Issue("AS-14023")
    @TmsLink("612")
    @Description("[C612] Password Policy by default - Administrator")
    @Test
    public void basicAdministratorTest() {
        AccountsPage accountsPage = adminUiSteps.login(administrator);
        assertThat(accountsPage)
                .withFailMessage("User is not logged in - Account page is not displayed")
                .isPageDisplayed();
    }

}
