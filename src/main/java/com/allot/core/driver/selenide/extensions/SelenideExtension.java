package com.allot.core.driver.selenide.extensions;

import com.allot.core.driver.selenide.config.DriverConfig;
import com.allot.core.driver.selenide.listeners.AllureSelenideListener;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SelenideExtension implements BeforeAllCallback, AfterEachCallback {

    private final DriverConfig driverConfig;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenideListener());
        driverConfig.updateSelenideConfiguration();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        WebDriverRunner.closeWebDriver();
    }

}
