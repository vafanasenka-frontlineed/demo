package com.allot.core.driver.selenide.extensions;

import com.allot.core.driver.selenide.config.DriverConfig;
import com.allot.core.driver.selenide.listeners.AllureSelenideListener;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SelenideExtension implements BeforeAllCallback, AfterEachCallback {

    @Autowired
    private DriverConfig driverConfig;

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
