package com.allot.domain.asm.frontend.page.acc;

import com.allot.core.driver.Page;
import com.codeborne.selenide.Selenide;

import static com.allot.core.driver.selenide.Actions.isElementDisplayed;
import static com.allot.domain.asm.frontend.page.acc.HomePage.HomePageElement.USER_MENU;


public class HomePage extends Page {

    @Override
    public boolean isPresent() {
        return isElementDisplayed(Selenide.$(USER_MENU.getLocator()));
    }

    @Override
    public String getUrlEndpoint() {
        return "#/dashboard";
    }

    public enum HomePageElement {

        USER_MENU(" .user__menu "),
        ;

        private String locator;

        HomePageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
