package com.allot.domain.asm.frontend.step;

import com.allot.domain.asm.frontend.model.*;
import com.allot.domain.asm.frontend.page.isp.*;
import com.allot.domain.asm.frontend.page.isp.account.EditAccountPage;
import com.allot.domain.asm.frontend.page.isp.navigation.NavigationMenu;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import java.util.List;


@Slf4j
@Component
public class AdminSteps {

    @Value("${admin.base.url}")
    private String baseUrl;

    @Value("${admin.user}")
    private String defaultUserName;

    @Value("${admin.password}")
    private String defaultUserPassword;

    @Autowired
    private NavigationMenu navigationMenu;

    @Step("Login to Secure Management Administration")
    public AccountsPage login(String user, String password) {
        log.info("Login to ISM administration with the user {} ", user);
        return Selenide
                .open(baseUrl, LoginPage.class)
                .login(user, password);
    }

    public AccountsPage login() {
        return login(defaultUserName, defaultUserPassword);
    }

    public AccountsPage login(User user) {
        return login(user.getName(), user.getPassword());
    }

    @Step("Add new account")
    public void addNewAccount(UserAccount userAccount) {
        log.info("Add new account {}", userAccount.getAccountId());
        navigationMenu.openAccounts()
                .clickAddNewAccount()
                .submitAccountInfo(userAccount);
    }

    @Step("Delete account")
    public void deleteAccount(UserAccount userAccount) {
        String accountId = userAccount.getAccountId();
        log.info("Delete existing account {}", accountId);
        navigationMenu.openAccounts().deleteAccount(accountId);
    }

    @Step("Get list of existing accounts")
    public List<UserAccount> getAccountList() {
        log.info("Get list of existing accounts");
        return navigationMenu.openAccounts().getAccountList();
    }

    @Step("Add new user to the account")
    public EditAccountPage addUserToAccount(UserAccount userAccount, User user) {
        log.info("Add user {} to the account {}", user.getName(), userAccount.getAccountId());
        return Selenide.page(AccountsPage.class).editAccount(userAccount.getAccountId()).addNewUser(user);
    }

    @Step("Delete user from the account")
    public void deleteUser(UserAccount userAccount, User user) {
        log.info("Delete user {} from the account {}", user.getName(), userAccount.getAccountId());
        Selenide.page(EditAccountPage.class).deleteUser(user);
    }

    @Step("Navigate to account and get list of devices")
    public List<UserDevice> getDeviceList(UserAccount userAccount) {
        log.info("Navigate to account {} and get list of devices", userAccount.getAccountId());
        List<UserDevice> deviceList = Selenide.page(AccountsPage.class)
                .editAccount(userAccount.getAccountId())
                .getUserDevicesList();
        navigationMenu.openAccounts();
        return deviceList;
    }

}
