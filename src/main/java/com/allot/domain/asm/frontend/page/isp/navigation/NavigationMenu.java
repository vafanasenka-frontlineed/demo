package com.allot.domain.asm.frontend.page.isp.navigation;

import com.allot.domain.asm.frontend.page.isp.AccountsPage;
import com.codeborne.selenide.*;
import org.springframework.stereotype.Component;

import static com.allot.domain.asm.frontend.page.isp.navigation.NavigationMenu.NavigationMenuElement.*;
import static com.codeborne.selenide.Selenide.$x;


@Component
public class NavigationMenu {

    public AccountsPage openAccounts() {
        SelenideElement accountManagement = $x(ACCOUNT_MANAGEMENT.getLocator());
        if (!accountManagement.is(Condition.attribute("aria-expanded"))) {
            accountManagement.contextClick();
            accountManagement.waitUntil(Condition.attribute("aria-expanded"), Configuration.timeout);
        }
        $x(ACCOUNTS.getLocator()).click();
        return Selenide.page(AccountsPage.class);
    }

    public enum NavigationMenuElement {

        NAVIGATION_BAR(" //ul[@class='nav'] "),
        ACCOUNT_MANAGEMENT(NAVIGATION_BAR.getLocator() + " //a[contains(@class, 'sidebar-menu-item')]["
                + "./p[contains(text(), 'Account Management')]] "),
        ACCOUNTS(NAVIGATION_BAR.getLocator() + " //a[@href= '/account_management/accounts'] "),
        ;

        private String locator;

        NavigationMenuElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
