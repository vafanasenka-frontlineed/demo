package com.allot.cs.common;

import com.allot.cs.CSBaseTest;
import com.allot.cs.page.HomePage;
import com.allot.cs.page.panel.NavigationPanel;
import com.allot.cs.step.LoginSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.allot.cs.page.HomePage.HomePageElement.LEFT_NAVIGATION_PANEL;

class LeftNavigationMenuTest extends CSBaseTest {

    @Autowired
    private LoginSteps loginSteps;

    @Feature(value = "[CS] Home page")
    @Issue("AS-16313")
    @Description("Check left menu in the UI")
    @Test
    void navigationMenuTest(SoftAssertions soft) {
        HomePage homePage = loginSteps.login();
        NavigationPanel navigationPanel = homePage.expandNavigationPanel();
        soft.assertThat(homePage.isElementDisplayed(LEFT_NAVIGATION_PANEL))
                .withFailMessage("Left Navigation panel is not displayed on the page")
                .isTrue();
        List<String> expectedL1Menu = Stream.of(NavigationPanel.L1MenuItem.values())
                .map(NavigationPanel.L1MenuItem::getName)
                .collect(Collectors.toList());
        soft.assertThat(navigationPanel.getL1MenuTitles())
                .withFailMessage("Navigation menu doesn't contain all required items")
                .hasSizeGreaterThan(0)
                .containsAnyElementsOf(expectedL1Menu);
        soft.assertAll();
    }

}