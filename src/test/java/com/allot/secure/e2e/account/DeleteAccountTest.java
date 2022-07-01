package com.allot.secure.e2e.account;

import com.allot.domain.asm.backend.rest.api.account.AccountApiService;
import com.allot.domain.asm.frontend.model.UserAccount;
import com.allot.domain.asm.frontend.step.AdminSteps;
import com.allot.secure.BaseTest;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.allot.core.testdata.TestDataSource.USER_ACCOUNT;


@Slf4j
class DeleteAccountTest extends BaseTest {

    @Autowired
    private AdminSteps adminUiSteps;
    @Autowired
    private AccountApiService accountService;
    private UserAccount userAccount;

    @BeforeAll
    public void addNewAccount() {
        userAccount = testDataProvider.getData(USER_ACCOUNT, "New_account");
        authService.authenticate();
        accountService.addAccount(userAccount);
    }

    @Feature(value = "[ISP GUI] Main Menu and Account Management")
    @Issue("ASM-2725")
    @TmsLink("222")
    @Description("[C222] Account Management: delete account")
    @Test
    void addNewAccountTest(SoftAssertions soft) {
        adminUiSteps.login();
        adminUiSteps.deleteAccount(userAccount);
        String accountId = userAccount.getAccountId();
        log.info("Check that the account was deleted and NOT displayed on the Accounts page");
        soft.assertThat(adminUiSteps.getAccountList())
                .extracting("accountId")
                .withFailMessage("The deleted account '%s' is displayed on the Accounts page, but not expected.",
                        accountId)
                .contains(accountId);
        log.info("Check that the deleted account is not present in the '/accounts' endpoint");
        soft.assertThat(accountService.getAccountsList())
                .filteredOn(account -> account.getAccountId().equals(accountId))
                .withFailMessage("The deleted account '%s' is present in API Accounts request", accountId)
                .hasSize(0);
        soft.assertAll();
    }

}
