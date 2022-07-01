package com.allot.secure.e2e.password.policy.account.extension;

import com.allot.core.testdata.TestDataSource;
import com.allot.domain.asm.backend.rest.api.account.AccountApiService;
import com.allot.domain.asm.backend.rest.api.admins.ManagerApiService;
import com.allot.domain.asm.backend.rest.api.session.SessionApiService;
import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.model.*;
import com.allot.domain.asm.frontend.step.AccountSteps;
import com.allot.secure.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class BaseAccountManagerTest extends BaseTest {

    @Autowired
    protected AccountApiService accountService;
    @Autowired
    protected ManagerApiService mangerService;
    @Autowired
    protected SessionApiService sessionService;
    @Autowired
    protected AccountSteps accountSteps;

    protected User manager;
    protected UserAccount account;

    @BeforeAll
    protected void addNewAccountAndManager() {
        manager = testDataProvider.getData(TestDataSource.USER, "Password_policy_manger");
        account = testDataProvider.getData(TestDataSource.USER_ACCOUNT, "Password_policy_account");
        log.info("Add new account {} and manager {} to the account.", account.getAccountId(), manager.getName());
        authService.authenticate();
        sessionService.setSessionConfiguration(SessionConfigurationRequest.setDefault());
        accountService.addAccount(account);
        mangerService.addManagerToAccount(manager, account);
    }

    @AfterAll
    protected void cleanUp() {
        authService.refreshAuthToken();
        log.info("Revert Password Policy configuration to the default");
        sessionService.setSessionConfiguration(SessionConfigurationRequest.setDefault());
        log.info("Delete account {} with the manager.", account.getAccountId());
        accountService.deleteAccount(account);
    }

}
