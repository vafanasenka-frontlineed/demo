package com.allot.domain.asm.frontend.page.isp.account;

import com.allot.domain.asm.frontend.model.UserDevice;
import com.allot.domain.asm.frontend.page.Tab;
import com.allot.domain.asm.frontend.page.table.Table;
import com.codeborne.selenide.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.allot.core.driver.selenide.Actions.isElementDisplayed;
import static com.allot.domain.asm.frontend.page.isp.account.DeviceTab.DevicesTabPageElement.DEVICES_TABLE;
import static com.allot.domain.asm.frontend.page.isp.account.UsersTab.UsersColumn.NAME;
import static com.allot.domain.asm.frontend.page.isp.account.UsersTab.UsersTabPageElement.TAB;
import static com.codeborne.selenide.Selenide.$;


public class DeviceTab implements Tab {

    @Override
    public boolean isActive() {
        return $(TAB.getLocator()).isDisplayed();
    }

    public List<UserDevice> getUserDevicesList() {
        SelenideElement table = Selenide.$(DEVICES_TABLE.getLocator());
        if (!isElementDisplayed(table)) {
            return Collections.emptyList();
        }
        Table deviceTable = new Table(table);
        return deviceTable.getRows()
                .stream()
                .map(row -> UserDevice.builder()
                        .description(deviceTable.getCellText(row, NAME.toString()))
                        .build()
                    ).collect(Collectors.toList());
    }

    public enum DevicesTabPageElement {

        TAB(" #Devices "),
        NEW_BUTTON(TAB.getLocator() + " button.btn-md "),
        DEVICES_TABLE(TAB.getLocator() + " .el-table "),
        ;

        private String locator;

        DevicesTabPageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
