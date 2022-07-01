package com.allot.core.driver.selenide.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public enum DriverConfigFactory {

    CHROME {
        @Override
        public DesiredCapabilities getBrowserCapabilities() {
            WebDriverManager.chromedriver().setup();
            return DesiredCapabilities.chrome();
        }
    },
    FIREFOX {
        @Override
        public DesiredCapabilities getBrowserCapabilities() {
            WebDriverManager.firefoxdriver().setup();
            return DesiredCapabilities.firefox();
        }
    },
    IE {
        @Override
        public DesiredCapabilities getBrowserCapabilities() {
            WebDriverManager.iedriver().setup();
            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                    true);
            ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            return ieCapabilities;
        }
    };

    public abstract DesiredCapabilities getBrowserCapabilities();

    public static DriverConfigFactory getInstance(String browserName) {
        return DriverConfigFactory.valueOf(browserName.toUpperCase());
    }

}
