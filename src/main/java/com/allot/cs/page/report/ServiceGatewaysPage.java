package com.allot.cs.page.report;

import com.allot.core.driver.Page;
import com.allot.cs.page.element.Dropdown;
import com.allot.cs.page.panel.FilterPanel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.allot.core.driver.selenide.Actions.elementWait;
import static com.allot.cs.model.filter.ServiceGatewayReport.*;
import static com.allot.cs.page.report.ServiceGatewaysPage.ServiceGatewaysPageElement.*;
import static com.codeborne.selenide.Selenide.*;


@Slf4j
public class ServiceGatewaysPage extends Page {

    @Getter
    public FilterPanel filterPanel;

    public ServiceGatewaysPage() {
        filterPanel = new FilterPanel();
    }

    @Override
    public boolean isPresent() {
        return $(TITLE.getLocator()).text().contains("Service Gateways");
    }

    @Override
    public String getUrlEndpoint() {
        return "";
    }

    public void setByViewOption(NetworkStatisticsByViewOption byFilter) {
        new Dropdown($x(BY_NETWORK_STATISTICS_VIEW.getLocator()))
                .setValue(byFilter.getValue());
        waitUntilPageLoaded();
    }

    public void setResolutionViewOption(ResolutionViewOption byFilter) {
        new Dropdown($x(RESOLUTION_NETWORK_STATISTICS_VIEW.getLocator()))
                .setValue(byFilter.getValue());
        waitUntilPageLoaded();
    }

    @Override
    public void waitUntilPageLoaded() {
        log.info("Wait until loader disappear...");
        elementWait().until(ExpectedConditions.invisibilityOf($(LOADER.getLocator())));
        log.info("Wait until Report Path is visible...");
        elementWait().until(ExpectedConditions.visibilityOf($(REPORT_PATH.getLocator())));
    }

    @Getter
    @AllArgsConstructor
    public enum ServiceGatewaysPageElement {

        TITLE(" #SheetTitle "),
        REPORT_PATH(" .mojoPath-path .pathCurrent "),
        BY_NETWORK_STATISTICS_VIEW("//div[@class='Container_drop_down' and .//a[text()='Service Gateways']]"),
        RESOLUTION_NETWORK_STATISTICS_VIEW("//*[@class='SelectorsContainer' and contains(text(),'Resolution')]"),
        LOADER(" .loader-message "),
        ;

        private final String locator;

    }

}
