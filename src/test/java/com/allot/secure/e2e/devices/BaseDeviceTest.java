package com.allot.secure.e2e.devices;

import com.allot.core.testdata.TestDataSource;
import com.allot.domain.asm.backend.rest.api.account.*;
import com.allot.domain.asm.frontend.model.*;
import com.allot.secure.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class BaseDeviceTest extends BaseTest {

    @Autowired
    protected ProvisionApiService provisionApiService;
    @Autowired
    protected AccountApiService accountService;
    protected UserAccount account;
    protected UserDevice userDevice;

    @BeforeAll
    protected void provisionNewAccountWithDevice() {
        account = testDataProvider.getData(TestDataSource.USER_ACCOUNT, "Provision_account");
        userDevice = testDataProvider.getData(TestDataSource.USER_DEVICE, "Provision_device");
        authService.authenticate();
        provisionApiService.provisionAccount(account, userDevice);
    }

    @AfterAll
    protected void deleteProvisionedAccount() {
        authService.refreshAuthToken();
        log.info("Delete account {}", account.getAccountId());
        accountService.deleteAccount(account);
    }

}
