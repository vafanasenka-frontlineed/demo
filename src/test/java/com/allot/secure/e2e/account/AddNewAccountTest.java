package com.allot.secure.e2e.account;

import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;


@Slf4j
class AddNewAccountTest extends BaseNewAccountTest {

    @Feature(value = "[ISP GUI] Main Menu and Account Management")
    @Issue("ASM-2679")
    @TmsLink("221")
    @Description("[C221] Account Management: new account can be created")
    @Test
    void addNewAccountTest(SoftAssertions soft) {
        adminUiSteps.login();
        adminUiSteps.addNewAccount(userAccount);
        String accountId = userAccount.getAccountId();
        log.info("Check that the added account is displayed on the Accounts page");
        soft.assertThat(adminUiSteps.getAccountList())
                .extracting("accountId")
                .withFailMessage("The Account '{}' is not displayed on the Accounts page", accountId)
                .contains(accountId);
        log.info("Check that the added account is present in the '/accounts' endpoint");
        soft.assertThat(accountService.getAccountsList())
                .filteredOn(account -> account.getAccountId().equals(accountId))
                .withFailMessage("The Account '{}' is not present in API Accounts request", accountId)
                .hasSize(1);
        soft.assertAll();
    }

}
