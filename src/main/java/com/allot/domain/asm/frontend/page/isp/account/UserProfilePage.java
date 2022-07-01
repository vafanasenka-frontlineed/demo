package com.allot.domain.asm.frontend.page.isp.account;

import com.allot.domain.asm.frontend.model.User;
import com.codeborne.selenide.Selenide;

import static com.allot.domain.asm.frontend.page.isp.account.UserProfilePage.UserProfilePageElement.*;


public class UserProfilePage {

    public EditAccountPage addUser(User user) {
        Selenide.$(NAME_INPUT.getLocator()).setValue(user.getName());
        Selenide.$(CREATE_BUTTON.getLocator()).click();
        return Selenide.page(EditAccountPage.class);
    }

    public enum UserProfilePageElement {

        NAME_INPUT(" #name "),
        CREATE_BUTTON(" #btn_submit ");

        private String locator;

        UserProfilePageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
