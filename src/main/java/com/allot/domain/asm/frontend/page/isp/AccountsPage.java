package com.allot.domain.asm.frontend.page.isp;

import com.allot.core.driver.Page;
import com.allot.core.driver.selenide.Actions;
import com.allot.domain.asm.frontend.model.UserAccount;
import com.allot.domain.asm.frontend.page.isp.account.*;
import com.allot.domain.asm.frontend.page.table.Table;
import com.codeborne.selenide.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import java.util.List;
import java.util.stream.Collectors;

import static com.allot.core.driver.selenide.Actions.*;
import static com.allot.domain.asm.frontend.page.isp.AccountsPage.AccountsColumn.*;
import static com.allot.domain.asm.frontend.page.isp.AccountsPage.AccountsPageElement.CONFIRM_BUTTON;
import static com.codeborne.selenide.Selenide.$;


@Slf4j
public class AccountsPage extends Page {

    public enum AccountsColumn {

        ACCOUNT,
        PRODUCTS,
        SERVICES,
        PROFILES,
        ;
    }

    @Override
    public boolean isPresent() {
        return isElementContainsText($(AccountsPageElement.NAVIGATION_BAR.getLocator()), "Accounts");
    }

    @Override
    public String getUrlEndpoint() {
        return "#/account_management/accounts";
    }

    public EditAccountPage editAccount(String accountId) {
        log.info("Click edit account with the name {}", accountId);
        SelenideElement editButton = getAccountsTable()
                .getRow(ACCOUNT.toString(), accountId)
                .getActionsCell()
                .getEditButton();
        Actions.clickViaJs(editButton);
        return Selenide.page(EditAccountPage.class);
    }

    public EditAccountPage deleteAccount(String accountId) {
        log.info("Find and delete account with the name {}", accountId);
        SelenideElement deleteButton = getAccountsTable()
                .getRow(ACCOUNT.toString(), accountId)
                .getActionsCell()
                .getDeleteButton();
        deleteButton.scrollTo();
        Actions.clickViaJs(deleteButton);
        Actions.clickViaJs(deleteButton);
        $(CONFIRM_BUTTON.getLocator()).shouldBe(Condition.visible).click();
        return Selenide.page(EditAccountPage.class);
    }

    public NewAccountPopUp clickAddNewAccount() {
        log.info("Click add 'New' account button");
        $(AccountsPageElement.NEW_ACCOUNT_BUTTON.getLocator()).click();
        return Selenide.page(NewAccountPopUp.class);
    }

    @Nullable
    public UserAccount getAccount(String accountName) {
        log.info("Get account info for '{}' user account ", accountName);
        return getAccountList().stream()
                .filter(account -> account.getAccountId().equals(accountName))
                .findFirst()
                .orElse(null);
    }

    public List<UserAccount> getAccountList() {
        log.info("Get accounts lists");
        Table accountsTable = getAccountsTable();
        return accountsTable.getRows()
                .stream()
                .map(row -> UserAccount.builder()
                        .accountId(accountsTable.getCellText(row, ACCOUNT.toString()))
                        .productList(accountsTable.getCellText(row, PRODUCTS.toString()))
                        .serviceList(accountsTable.getCellText(row, SERVICES.toString()))
                        .profileList(accountsTable.getCellText(row, PROFILES.toString()))
                        .build())
                .collect(Collectors.toList());
    }

    private Table getAccountsTable() {
        SelenideElement tableElement = $(AccountsPageElement.ACCOUNTS_TABLE.getLocator());
        if (!isElementDisplayed(tableElement)) {
            throw new RuntimeException("Accounts table is not displayed!");
        }
        return new Table(tableElement);
    }

    public enum AccountsPageElement {

        NEW_ACCOUNT_BUTTON(" div.container button "),
        PASSWORD_INPUT(" #password "),
        SUBMIT_BUTTON(" #btn_submit "),
        ACCOUNTS_TABLE(" .el-table "),
        NAVIGATION_BAR(" .navbar-brand "),
        CONFIRM_BUTTON(" #confirm___BV_modal_content_ .btn-primary "),
        ;

        private String locator;

        AccountsPageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
