package com.allot.core.driver.selenide.config;

import com.allot.util.HttpUrlBuilder;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.allot.util.HttpUrlBuilder.HTTPSchema.HTTP;


@Component
public class DriverConfig {

    @Value("${browser}")
    private String browserName;

    @Value("${browser.version}")
    private String browserVersion;

    @Value("${startMaximized:true}")
    private boolean startMaximized;

    @Value("${screenshots:true}")
    private boolean screenshots;

    @Value("${savePageSource:false}")
    private boolean savePageSource;

    @Value("${wait.timeout.milliseconds}")
    private int timeout;

    @Value("${pageLoadStrategy}")
    private String loadStrategy;

    @Value("${headless:false}")
    private boolean headless;

    @Value("${driver.remote:false}")
    private boolean isDriverRemote;

    @Value("${docker.host}")
    private String dockerHost;

    @Value("${docker.port}")
    private int dockerPort;

    @Value("${docker.path}")
    private String dockerPath;

    public void updateSelenideConfiguration() {
        Configuration.startMaximized = startMaximized;
        Configuration.browser = browserName;
        Configuration.browserVersion = browserVersion;
        Configuration.timeout = timeout;
        Configuration.screenshots = screenshots;
        Configuration.savePageSource = savePageSource;
        Configuration.headless = headless;
        Configuration.pageLoadStrategy = loadStrategy;
        if (isDriverRemote) {
            Configuration.remote = HttpUrlBuilder.build(HTTP, dockerHost, dockerPort, dockerPath);
            Configuration.driverManagerEnabled = false;
        }
        var browserDesiredCapabilities = DriverConfigFactory.getInstance(browserName).getBrowserCapabilities();
        Configuration.browserCapabilities = getCommonDesiredCapabilities().merge(browserDesiredCapabilities);
    }

    private DesiredCapabilities getCommonDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        capabilities.setVersion(browserVersion);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        if (isDriverRemote) {
            capabilities.setCapability("enableVNC", true);
        }
        return capabilities;
    }

}
