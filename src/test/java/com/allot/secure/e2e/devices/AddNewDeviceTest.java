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
public class AddNewDeviceTest extends BaseDeviceTest {

    @Autowired
    private AdminSteps adminSteps;

    @Feature(value = "[E2E Tests] ISP General View and General Management - Devices")
    @Issue("ASM-2618")
    @TmsLink("C401")
    @Description("[C401] Devices - ISP - Add new devices")
    @Test
    public void addNewDeviceTest() {
        adminSteps.login();
        List<UserDevice> userDeviceList = adminSteps.getDeviceList(account);
        assertThat(userDeviceList)
                .withFailMessage("User devices count is not correct. Only one device were provisioned! Actual device "
                        + "list size is = %s.", userDeviceList.size())
                .hasSize(1);
        assertThat(userDeviceList)
                .extracting("description")
                .withFailMessage("User device name is not expected")
                .containsOnly(userDevice.getDescription());
    }

}
