package com.allot.domain.asm.frontend.page.isp.account;

import com.allot.core.driver.Page;
import com.allot.domain.asm.frontend.model.*;
import com.allot.domain.asm.frontend.page.Tab;
import com.allot.domain.asm.frontend.page.isp.AccountsPage;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import java.util.List;


public class EditAccountPage extends Page {

    @Override
    public String getUrlEndpoint() {
        return "#/accounts/edit";
    }

    public <T extends Tab> T openTab(AccountTabs tab) {
        return (T) Tab.open(tab.getClazz(), tab.getIndex());
    }

    @Override
    public boolean isPresent() {
        return WebDriverRunner.url().endsWith(getUrlEndpoint());
    }

    @Step("Add a new user to the account")
    public EditAccountPage addNewUser(User user) {
        this.<UsersTab>openTab(AccountTabs.USERS)
                .clickNewButton()
                .addUser(user);
        return Selenide.page(EditAccountPage.class);
    }

    @Step("Delete user from the account")
    public AccountsPage deleteUser(User user) {
        UsersTab userTab = openTab(AccountTabs.USERS);
        userTab.deleteUser(user);
        return Selenide.page(AccountsPage.class);
    }

    @Step("Read user list from the account")
    public List<User> getUserList() {
        UsersTab userTab = openTab(AccountTabs.USERS);
        return userTab.getUserList();
    }

    @Step("Get user devices list from the account")
    public List<UserDevice> getUserDevicesList() {
        DeviceTab deviceTab = openTab(AccountTabs.DEVICES);
        return deviceTab.getUserDevicesList();
    }

}
