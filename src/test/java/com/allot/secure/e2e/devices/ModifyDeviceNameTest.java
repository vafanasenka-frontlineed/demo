package com.allot.secure.e2e.devices;

import com.allot.domain.asm.frontend.model.UserDevice;
import com.allot.domain.asm.frontend.step.AdminSteps;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class ModifyDeviceNameTest extends BaseDeviceTest {

    @Autowired
    private AdminSteps adminSteps;
    private static final String DEVICE_NAME_TO_UPDATE = "New updated device name";

    @Feature(value = "[E2E Tests] ISP General View and General Management - Devices")
    @Issue("ASM-2626")
    @TmsLink("C403")
    @Description("[C403] Edit Devices - ISP - Modify device name")
    @Test
    public void modifyDeviceNameTest() {
        accountService.updateAccountDeviceName(userDevice, DEVICE_NAME_TO_UPDATE);
        adminSteps.login();
        List<UserDevice> userDeviceList = adminSteps.getDeviceList(account);
        assertThat(userDeviceList)
                .withFailMessage("User devices count is not correct. Only one device were provisioned! Actual device "
                        + "list size is = %s.", userDeviceList.size())
                .hasSize(1);
        assertThat(userDeviceList)
                .extracting("description")
                .withFailMessage("User device name was not updated")
                .containsOnly(DEVICE_NAME_TO_UPDATE);
    }

}
