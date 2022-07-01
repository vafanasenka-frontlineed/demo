package com.allot.cs.page;

import com.allot.core.driver.Page;
import com.allot.core.driver.selenide.Actions;
import com.allot.cs.page.panel.NavigationPanel;
import com.codeborne.selenide.Selenide;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.allot.core.driver.selenide.Actions.elementWait;
import static com.allot.cs.page.HomePage.HomePageElement.*;
import static com.codeborne.selenide.Selenide.$;


@Slf4j
public class HomePage extends Page {

    @Override
    public boolean isPresent() {
        return Actions.isElementDisplayed($(MENU_BAR.getLocator()));
    }

    @Override
    public String getUrlEndpoint() {
        return "/ClearSee/servlet/mstrWeb";
    }

    public String getUserName() {
        log.info("Get current user name");
        return RegExUtils.removePattern($("#mstrPathAccount2").getText(), "( \\[sign out\\])");
    }

    public LogoutForm logout() {
        log.info("Click log out button");
        $(LOGOUT_LINK.getLocator()).click();
        return Selenide.page(LogoutForm.class);
    }

    public boolean isElementDisplayed(@NonNull final HomePageElement pageElement) {
        String locator = pageElement.getLocator();
        log.info("Check if element by locator {} is displayed on the Home page", locator);
        return Actions.isElementDisplayed($(locator));
    }

    public String getReportName() {
        return $(REPORT_PATH.getLocator()).getText();
    }

    public void waitUntilPageLoaded() {
        log.info("Wait until Home page overlay is not visible");
        elementWait().until(ExpectedConditions.invisibilityOf($(OVERLAY.getLocator())));
        log.info("Wait until Home page Menu is visible");
        elementWait().until(ExpectedConditions.visibilityOf($(LEFT_NAVIGATION_PANEL.getLocator())));
    }

    public NavigationPanel expandNavigationPanel() {
        log.info("Expand Left Navigation panel by hovering");
        $(LEFT_NAVIGATION_PANEL.getLocator()).hover();
        return Selenide.page(NavigationPanel.class);
    }

    @Getter
    @AllArgsConstructor
    public enum HomePageElement {
        OVERLAY(" .scriptsMask "),
        MENU_BAR(" .mstrmojo-RootView-menubar "),
        LEFT_NAVIGATION_PANEL(" .masterdiv "),
        LOGOUT_LINK(" #userlogout "),
        LOGO(" .mstrLogo "),
        USER_NAME(" #mstrPathAccount2 "),
        REPORT_PATH(" .mojoPath-path .pathCurrent "),
        ;

        private String locator;

    }

}
