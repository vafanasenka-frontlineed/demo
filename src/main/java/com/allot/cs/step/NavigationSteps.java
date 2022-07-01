package com.allot.cs.step;

import com.allot.cs.model.Report;
import com.allot.cs.page.HomePage;
import com.allot.cs.page.panel.NavigationPanel;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class NavigationSteps {

    public <T> T openReportPage(Report report, Class<T> pageClazz) {
        String reportTitle =  report.getTitle();
        log.info("Open '{}' report page", reportTitle);
        HomePage homePage = Selenide.page(HomePage.class);
        if (!homePage.getReportName().startsWith(reportTitle)) {
            NavigationPanel navigationPanel = homePage.expandNavigationPanel();
            navigationPanel.clickL1(report.getL1Menu());
            navigationPanel.clickL2(report.getL1Menu(), reportTitle);
        }
        return Selenide.page(pageClazz);
    }

}
