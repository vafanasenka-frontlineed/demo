package com.allot.domain.asm.frontend.page.isp;

import com.allot.core.driver.Page;
import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.allot.core.driver.selenide.Actions.isElementDisplayed;
import static com.allot.domain.asm.frontend.page.isp.LoginPage.LoginPageElement.*;


@Slf4j
public class LoginPage extends Page {

    private static final String CAPTURE_VALUE = "1";

    @Step("Login to Secure Management Administration with {name}")
    public AccountsPage login(String name, String password) {
        Selenide.$(USER_INPUT.getLocator()).setValue(name);
        Selenide.$(PASSWORD_INPUT.getLocator()).setValue(password);
        SelenideElement captureElement = Selenide.$(CAPTURE_INPUT.getLocator());
        if (captureElement.exists()) {
            captureElement.setValue(CAPTURE_VALUE);
        }
        Selenide.$(SUBMIT_BUTTON.getLocator()).click();
        return Selenide.page(AccountsPage.class);
    }

    @Override
    public String getUrlEndpoint() {
        return "#/login";
    }

    @Step("Check if Login Page is present")
    @Override
    public boolean isPresent() {
        return isElementDisplayed(Selenide.$(USER_INPUT.getLocator()));
    }

    public enum LoginPageElement {

        USER_INPUT(" #username "),
        PASSWORD_INPUT(" #password "),
        SUBMIT_BUTTON(" #btn_submit "),
        CAPTURE_INPUT(" #captcha "),
        ;

        private String locator;

        LoginPageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
