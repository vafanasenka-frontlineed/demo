package com.allot.secure.e2e.password.policy.account;

import com.allot.domain.asm.frontend.page.acc.HomePage;
import com.allot.secure.e2e.password.policy.account.extension.BaseAccountManagerTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static com.allot.core.assertj.PageAssert.assertThat;


public class BasicAccountTest extends BaseAccountManagerTest {

    @Feature(value = "[E2E Tests] ISP General View and General Management - Password Policy")
    @Issue("AS-14023")
    @TmsLink("611")
    @Description("[C611] Password Policy by default - Administrator")
    @Test
    public void basicAccountTest() {
        HomePage homePage = accountSteps.loginToAccount(manager);
        assertThat(homePage)
                .withFailMessage("Manger [%s] is not logged in to the ASM Account", manager.getName())
                .isPageDisplayed();
    }

}
