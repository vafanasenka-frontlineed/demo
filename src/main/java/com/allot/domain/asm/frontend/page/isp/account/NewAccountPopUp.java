package com.allot.domain.asm.frontend.page.isp.account;

import com.allot.domain.asm.frontend.model.UserAccount;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.allot.domain.asm.frontend.page.isp.account.NewAccountPopUp.NewAccountPopUpElements.*;


@Slf4j
public class NewAccountPopUp {

    @Step("Add new account info and submit")
    public void submitAccountInfo(UserAccount userAccount) {
        String accountId = userAccount.getAccountId();
        String accountType = userAccount.getType().getUiValue();
        log.info("Fill account info :/n account id = '{}', account type = '{}'", accountId, accountType);
        Selenide.$(ID_INPUT.getLocator()).setValue(accountId);
        SelenideElement dropdown = Selenide.$(ACCOUNT_TYPE_DROPDOWN.getLocator());
        dropdown.click();
        dropdown.shouldBe(Condition.attributeMatching("class", ".* is-focus"));
        ElementsCollection values = Selenide.$$(ACCOUNT_TYPE_VALUES.getLocator());
        values.stream()
                .filter(value -> value.getText().equalsIgnoreCase(accountType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account type doesn't contain option " + userAccount.getAccountId()))
                .click();
        Selenide.$(OK_BUTTON.getLocator()).click();
    }


    public enum NewAccountPopUpElements {

        BODY(" .modal-body "),
        ID_INPUT(BODY.getLocator() + " [name='Account ID'] "),
        ACCOUNT_TYPE_DROPDOWN(BODY.getLocator() + " .el-select  .el-input "),
        ACCOUNT_TYPE_VALUES(" #modal-new___BV_modal_outer_~div.el-select-dropdown li "),
        OK_BUTTON(" .modal-footer button.btn-primary "),
        ;

        private String locator;

        NewAccountPopUpElements(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
