package com.allot.core.driver;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static com.allot.core.driver.selenide.Actions.elementWait;
import static com.allot.cs.page.HomePage.HomePageElement.LEFT_NAVIGATION_PANEL;
import static com.allot.cs.page.HomePage.HomePageElement.OVERLAY;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;



public abstract class Page {

    public void waitDocumentReady() {
        Wait()
                .pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofMillis(6000))
                .until(webDriver -> Selenide.executeJavaScript("return document.readyState").equals("complete"));
    }

    public void waitUntilPageLoaded() {
        elementWait().until(ExpectedConditions.invisibilityOf($(OVERLAY.getLocator())));
        elementWait().until(ExpectedConditions.visibilityOf($(LEFT_NAVIGATION_PANEL.getLocator())));
    }


    protected Page() {
        waitDocumentReady();
    }

    public abstract boolean isPresent();

    public abstract String getUrlEndpoint();

}
