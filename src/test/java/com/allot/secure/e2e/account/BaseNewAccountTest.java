package com.allot.secure.e2e.account;

import com.allot.domain.asm.backend.rest.api.account.AccountApiService;
import com.allot.domain.asm.frontend.model.UserAccount;
import com.allot.domain.asm.frontend.step.AdminSteps;
import com.allot.secure.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.allot.core.testdata.TestDataSource.USER_ACCOUNT;


@Slf4j
public class BaseNewAccountTest extends BaseTest {

    @Autowired
    protected AdminSteps adminUiSteps;
    @Autowired
    protected AccountApiService accountService;
    protected UserAccount userAccount;

    @BeforeAll
    public void testData() {
        userAccount = testDataProvider.getData(USER_ACCOUNT, "New_account");
        authService.authenticate();
    }

    @AfterAll
    protected void deleteAccount() {
        authService.refreshAuthToken();
        log.info("Delete created account {}.", userAccount.getAccountId());
        accountService.deleteAccount(userAccount);
    }

}
