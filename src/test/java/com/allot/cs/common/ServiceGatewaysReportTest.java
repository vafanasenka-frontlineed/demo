package com.allot.cs.common;

import com.allot.core.assertj.PageAssert;
import com.allot.cs.CSBaseTest;
import com.allot.cs.model.Report;
import com.allot.cs.page.panel.FilterPanel;
import com.allot.cs.page.report.ServiceGatewaysPage;
import com.allot.cs.step.*;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.allot.cs.model.filter.ServiceGatewayReport.NetworkStatisticsByViewOption.SERVICE_GATEWAYS;
import static com.allot.cs.model.filter.ServiceGatewayReport.ResolutionViewOption.HOURLY;

class ServiceGatewaysReportTest extends CSBaseTest {

    @Autowired
    private LoginSteps loginSteps;
    @Autowired
    private NavigationSteps navigationSteps;

    @BeforeAll
    public void authorize() {
        loginSteps.login();
    }

    @Feature(value = "[CS] Reports")
    @Issue("AS-18002")
    @TmsLink("C6030")
    @Description("Filtering: Service gateways")
    @Test
    void serviceGatewaysReportTest() {
        ServiceGatewaysPage reportPage = navigationSteps
                .openReportPage(Report.NETWORK_SERVICE_GATEWAY_AND_POLICIES, ServiceGatewaysPage.class);
        PageAssert.assertThat(reportPage).isPageDisplayed();
        reportPage.setByViewOption(SERVICE_GATEWAYS);
        reportPage.setResolutionViewOption(HOURLY);
        FilterPanel filterPanel = reportPage.getFilterPanel();
        Assertions.assertThat(filterPanel.getResolution())
                .isEqualTo(HOURLY.getValue());
        Assertions.assertThat(filterPanel.getActiveFilters())
                .hasSize(1)
                .anyMatch(filter -> filter.contains("Service Gateway"));
    }

}