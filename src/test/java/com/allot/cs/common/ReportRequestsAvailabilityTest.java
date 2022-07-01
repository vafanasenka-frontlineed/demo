package com.allot.cs.common;

import com.allot.cs.CSBaseTest;
import com.allot.cs.http.ClearSeeHttpRequest;
import com.allot.cs.model.Report;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;

import static com.allot.core.assertj.ReportAssert.assertThat;

class ReportRequestsAvailabilityTest extends CSBaseTest {

    @Autowired
    private ClearSeeHttpRequest httpRequest;

    @BeforeAll
    public void authorize() {
        httpRequest.authorize();
    }

    @Feature(value = "[CS] Reports")
    @Issue("AS-17020")
    @Description("Reports availability by http")
    @ParameterizedTest
    @EnumSource(Report.class)
    void reportTest(Report report) {
        Response response = httpRequest.openReport(report);
        assertThat(response)
                .as("Verify {} response", report)
                .assertResponse();
        Assertions.assertThat(response.getBody().asString())
                .as("Check opened report page title")
                .contains(report.getTitle());
    }

}