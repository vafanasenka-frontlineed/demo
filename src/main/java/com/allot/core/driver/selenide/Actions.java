package com.allot.core.driver.selenide;

import com.codeborne.selenide.*;
import io.vavr.control.Try;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.Wait;


public class Actions {

    public static void clickViaJs(SelenideElement selenideElement) {
        Selenide.executeJavaScript("arguments[0].click();", selenideElement);
    }

    public static boolean isElementDisplayed(SelenideElement selenideElement) {
        return isElementDisplayed(selenideElement, 1500);
    }

    public static boolean isElementDisplayed(SelenideElement selenideElement, long timeout) {
        return Try.of(() -> selenideElement
                .waitUntil(Condition.visible, timeout))
                .isSuccess();
    }

    public static boolean isElementContainsText(SelenideElement selenideElement, String expectedText) {
        return Try.of(() -> selenideElement
                .shouldBe(Condition.text(expectedText)))
                .isSuccess();
    }

    public static FluentWait<WebDriver> elementWait(){
        return Wait()
                .ignoring(TimeoutException.class, NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(1));
    }

}
