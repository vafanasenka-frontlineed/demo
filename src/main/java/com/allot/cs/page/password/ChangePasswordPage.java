package com.allot.cs.page.password;

import com.allot.core.driver.Page;
import com.allot.core.driver.selenide.Actions;
import com.codeborne.selenide.Selenide;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import static com.allot.cs.page.password.ChangePasswordPage.ChangePasswordPageElement.*;

@Slf4j
public class ChangePasswordPage extends Page {

    @Override
    public boolean isPresent() {
        return Actions.isElementDisplayed(Selenide.$(CHANGE_PASSWORD_FORM.getLocator()));
    }

    @Override
    public String getUrlEndpoint() {
        return "";
    }

    public boolean updatePassword(String oldPassword, String password) {
        Selenide.$(OLD_PASSWORD_INPUT.getLocator()).setValue(oldPassword);
        Selenide.$(NEW_PASSWORD_INPUT.getLocator()).setValue(password);
        Selenide.$(NEW_PASSWORD_VERIFICATION_INPUT.getLocator()).setValue(password);
        Selenide.$(CHANGE_PASSWORD_BUTTON.getLocator()).click();
        if (Selenide.page(ChangedPasswordPanel.class).getSuccessMessage().isDisplayed()) {
            Selenide.page(ChangedPasswordPanel.class).clickContinueLink();
            log.info("Password [{}] was changed to [{}] successfully", oldPassword, password);
            return true;
        }
        log.error("Password [{}] was NOT changed to [{}] ", oldPassword, password);
        return false;
    }

    @Getter
    @AllArgsConstructor
    public enum ChangePasswordPageElement {
        CHANGE_PASSWORD_FORM(" #changePasswordForm "),
        OLD_PASSWORD_INPUT(" [name='Pwd'] "),
        NEW_PASSWORD_INPUT(" [name='newPwd'] "),
        NEW_PASSWORD_VERIFICATION_INPUT(" [name='checkPwd'] "),
        CHANGE_PASSWORD_BUTTON(" #ChangePwd "),
        ;

        private final String locator;

    }

}