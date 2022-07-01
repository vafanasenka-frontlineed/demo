package com.allot.cs.page;

import com.allot.core.driver.Page;
import com.allot.core.driver.selenide.Actions;
import com.allot.cs.page.password.ChangePasswordPage;
import com.codeborne.selenide.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import static com.allot.cs.page.PreferencePage.PreferencePageElement.*;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class PreferencePage extends Page {

    @Override
    public boolean isPresent() {
        return Actions.isElementDisplayed($x(PREFERENCE_FORM.getLocator()));
    }

    @Override
    public String getUrlEndpoint() {
        return "/ClearSee/servlet/mstrWeb";
    }

    private SelenideElement getChangePasswordButton() {
        return $x(CHANGE_PASS_BUTTON.getLocator());
    }

    public ChangePasswordPage clickChangePasswordButton() {
        getChangePasswordButton().click();
        return Selenide.page(ChangePasswordPage.class);
    }

    @Getter
    @AllArgsConstructor
    public enum PreferencePageElement {
        PREFERENCE_FORM("//form[@id= 'PreferenceForm']"),
        LEFT_NAVIGATION_PANEL("//*[@id='mstrWeb_dockLeft']"),
        CHANGE_PASS_BUTTON(LEFT_NAVIGATION_PANEL.getLocator() + "//a[@class='mstrLink']"),
        ;

        private String locator;

    }

}