package com.allot.cs.page;

import com.allot.core.driver.Page;
import com.allot.core.driver.selenide.Actions;
import com.codeborne.selenide.Selenide;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

import static com.allot.cs.page.LoginPage.LoginPageElement.*;
import static com.codeborne.selenide.Selenide.$;


@Slf4j
public class LoginPage extends Page {

    @Override
    public boolean isPresent() {
        return Actions.isElementDisplayed($(USER_INPUT.getLocator()));
    }

    @Override
    public String getUrlEndpoint() {
        return "Login";
    }

    public HomePage login(@NonNull final String name, @NonNull final String password) {
        log.info("Enter user name '{}', user password *** and submit login form", name);
        $(USER_INPUT.getLocator()).setValue(name);
        $(PASSWORD_INPUT.getLocator()).setValue(password);
        $(SUBMIT_BUTTON.getLocator()).click();
        return Selenide.page(HomePage.class);
    }

    public boolean isWrongPasswordMessageDisplayed() {
        log.info("Check if the incorrect password message is displayed on the login page");
        return $(WRONG_PASSWORD_MESSAGE.getLocator()).isDisplayed();
    }

    @Getter
    @AllArgsConstructor
    public enum LoginPageElement {

        USER_INPUT(" #Uid "),
        PASSWORD_INPUT(" #Pwd "),
        SUBMIT_BUTTON(" .mstrButton[type='submit'] "),
        WRONG_PASSWORD_MESSAGE(" .wrongpassword "),
        ;

        private String locator;

    }

}
