package com.allot.domain.asm.frontend.page.acc;

import com.allot.core.driver.Page;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.allot.core.driver.selenide.Actions.isElementDisplayed;
import static com.allot.domain.asm.frontend.page.isp.LoginPage.LoginPageElement.*;
import static com.codeborne.selenide.Selenide.$;


@Slf4j
public class LoginPage extends Page {

    public HomePage login(String name, String password) {
        $(USER_INPUT.getLocator()).setValue(name);
        $(PASSWORD_INPUT.getLocator()).setValue(password);
        $(SUBMIT_BUTTON.getLocator()).click();
        return Selenide.page(HomePage.class);
    }

    @Override
    public String getUrlEndpoint() {
        return "#/login";
    }

    @Step("Check if Login Page is present")
    public boolean isPresent() {
        return isElementDisplayed($(USER_INPUT.getLocator()));
    }

}
