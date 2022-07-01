package com.allot.secure.e2e.devices;

import com.allot.core.testdata.TestDataSource;
import com.allot.domain.asm.frontend.model.*;
import com.allot.domain.asm.frontend.step.AdminSteps;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Slf4j
class AssignDeviceToAnotherAccountTest extends BaseDeviceTest {

    @Autowired
    private AdminSteps adminSteps;
    protected UserAccount account2;
    protected UserDevice deviceAccount2;

    @BeforeAll
    public void preconditions() {
        account2 = testDataProvider.getData(TestDataSource.USER_ACCOUNT, "Provision_account2");
        deviceAccount2 = testDataProvider.getData(TestDataSource.USER_DEVICE, "Provision_tablet");
        log.info("Provision another account with one device");
        provisionApiService.provisionAccount(account2, deviceAccount2);
    }

    @Feature(value = "[E2E Tests] ISP General View and General Management - Devices")
    @Issue("ASM-2651")
    @TmsLink("C406")
    @Description("[C406] Devices - ISP - Reassign device to other account")
    @Test
    void assignDeviceToAnotherAccountTest(SoftAssertions soft) {
        accountService.reassignDeviceToAnotherAccount(userDevice, account2);
        adminSteps.login();
        log.info("Check that no devices left for account1");
        List<UserDevice> deviceListAccount1 = adminSteps.getDeviceList(account);
        soft.assertThat(deviceListAccount1)
                .withFailMessage("The account1 should not contain any devices!. Actual device list size is %s. ",
                        deviceListAccount1.size())
                .hasSize(0);
        log.info("Check that account2 has assigned device");
        List<UserDevice> deviceListAccount2 = adminSteps.getDeviceList(account2);
        soft.assertThat(deviceListAccount2)
                .withFailMessage("The account2 should contain two devices! Actual device list size is %s.",
                        deviceListAccount1.size())
                .hasSize(2);
        soft.assertThat(deviceListAccount2)
                .extracting("description")
                .withFailMessage("The account2 should contain 2 devices!")
                .contains(userDevice.getDescription(), deviceAccount2.getDescription());
        soft.assertAll();
    }

    @AfterAll
    public void cleanUp() {
        log.info("Delete account {}", account2.getAccountId());
        accountService.deleteAccount(account2);
    }

}
