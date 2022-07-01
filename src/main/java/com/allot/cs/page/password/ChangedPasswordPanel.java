package com.allot.cs.page.password;

import com.allot.core.driver.selenide.Actions;
import com.codeborne.selenide.*;
import lombok.*;

import static com.allot.cs.page.password.ChangedPasswordPanel.ContinuePageElement.*;
import static com.codeborne.selenide.Selenide.*;

public class ChangedPasswordPanel {

    public boolean isPresent() {
        return Actions.isElementDisplayed($(CONTINUE_LINK.getLocator()));
    }

    public void clickContinueLink() {
        $(CONTINUE_LINK.getLocator()).click();
    }

    public SelenideElement getSuccessMessage() {
        return $(SUCCESS_MESSAGE.getLocator());
    }

    @Getter
    @AllArgsConstructor
    public enum ContinuePageElement {
        CONTINUE_LINK(" .mstrPanelBody .mstrLink "),
        SUCCESS_MESSAGE("#changePassword_ChangePasswordPreferencesStyle > div > div.mstrPanelBody > div"),
        ERROR_MASSAGE("//*[@id='mstrWeb_error']"),
        ;

        private final String locator;

    }

}