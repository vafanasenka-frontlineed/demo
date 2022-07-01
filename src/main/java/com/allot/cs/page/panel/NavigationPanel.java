package com.allot.cs.page.panel;

import com.allot.cs.page.*;
import com.codeborne.selenide.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.*;

import static com.allot.cs.page.panel.NavigationPanel.NavigationPanelElement.*;
import static com.allot.cs.page.panel.NavigationPanel.leftPaneBottomBar.*;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class NavigationPanel {

    public List<String> getL1MenuTitles() {
        return getL1MenuElements().stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    private List<SelenideElement> getL1MenuElements() {
        return Stream.concat($$x(L1_EXPANDABLE.getLocator()).stream(), $$x(L1_NON_EXPANDABLE.getLocator()).stream())
                .collect(Collectors.toList());
    }

    public void clickL1(L1MenuItem l1MenuItem) {
        getL1MenuElements().stream().filter(element -> element.text().equals(l1MenuItem.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Navigation menu doesn't have " + l1MenuItem))
                .click();
    }

    public void clickL2(L1MenuItem l1MenuItem, String l2Name) {
        getL1MenuElements().stream().filter(element -> element.text().equals(l1MenuItem.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Navigation menu doesn't have " + l1MenuItem))
                .click();
    }

    private SelenideElement getPreferenceButton() {
        return $x(PREFERENCES_BUTTON.getLocator());
    }

    public PreferencePage clickPreferenceButton() {
        getPreferenceButton().click();
        return Selenide.page(PreferencePage.class);
    }

    @Getter
    @AllArgsConstructor
    public enum L1MenuItem {

        NETWORK("Network"),
        SUBSCRIBER("Subscriber"),
        EXPIRIENCE("Experience"),
        SECURITY("Security"),
        SELF_SERVICE("Self Service"),
        SHARED_REPORTS("Shared Reports"),
        HISTORY("History List"),
        SUBSCRIPTIONS("My Subscriptions");

        private final String name;

    }

    @Getter
    @AllArgsConstructor
    public enum leftPaneBottomBar {

        PREFERENCES_BUTTON("//a[@class = 'preferencesButton']"),
        HELP_BUTTON("//a[@class = 'helpButton']");

        private final String locator;

    }

    @Getter
    @AllArgsConstructor
    public enum NavigationPanelElement {

        CONTAINER(" //ul[@class='masterul'] "),
        L1_EXPANDABLE(CONTAINER.getLocator() + " /li/span/span[@class='docLeftTitle'] "),
        L1_NON_EXPANDABLE(CONTAINER.getLocator() + " /li/a/span[@class='docLeftTitle'] "),
        ;

        private final String locator;

    }

}