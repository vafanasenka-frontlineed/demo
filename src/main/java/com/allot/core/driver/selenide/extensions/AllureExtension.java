package com.allot.core.driver.selenide.extensions;

import com.allot.util.HttpUrlBuilder;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.allot.util.HttpUrlBuilder.HTTPSchema.HTTP;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;


@Slf4j
@Component
public class AllureExtension implements AfterAllCallback {

    @Value("${browser}")
    private String browserName;

    @Value("${browser.version}")
    private String browserVersion;

    @Value("${app.host}")
    private String host;

    @Value("${app.path}")
    private String path;

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        log.debug("Add environment data to Allure report");
        String baseUrl = HttpUrlBuilder.build(HTTP, host, 8080, path);
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browserName)
                        .put("Browser.version", browserVersion)
                        .put("URL", baseUrl)
                        .build());
    }

}
