package com.allot.core.driver.selenide.listeners;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.*;
import io.qameta.allure.*;
import io.qameta.allure.model.*;
import io.qameta.allure.util.ResultsUtils;
import org.openqa.selenium.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class AllureSelenideListener implements LogEventListener {

    private final AllureLifecycle lifecycle;

    public AllureSelenideListener() {
        this(Allure.getLifecycle());
    }

    public AllureSelenideListener(final AllureLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public void afterEvent(final LogEvent event) {
        lifecycle.getCurrentTestCase().ifPresent(uuid -> {
            final String stepUUID = UUID.randomUUID().toString();
            lifecycle.startStep(stepUUID, new StepResult()
                    .setName(event.toString())
                    .setStatus(Status.PASSED));
            lifecycle.updateStep(stepResult -> stepResult.setStart(stepResult.getStart() - event.getDuration()));
            if (LogEvent.EventStatus.FAIL.equals(event.getStatus())) {
                lifecycle.addAttachment("Screenshot", "image/png", "png", getScreenshotBytes());
                lifecycle.addAttachment("Page source", "text/html", "html", getPageSourceBytes());
                lifecycle.updateStep(stepResult -> {
                    final StatusDetails details = ResultsUtils.getStatusDetails(event.getError())
                            .orElse(new StatusDetails());
                    stepResult.setStatus(Status.FAILED);
                    stepResult.setStatusDetails(details);
                });
            }
            lifecycle.stopStep(stepUUID);
        });
    }

    @Override
    public void beforeEvent(LogEvent logEvent) {

    }

    private static byte[] getScreenshotBytes() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private static byte[] getPageSourceBytes() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

}
