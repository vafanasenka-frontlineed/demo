package com.allot.cs.page;

import com.allot.core.driver.Page;
import com.codeborne.selenide.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import static com.allot.cs.page.LogoutForm.LogoutFormElements.TITLE;
import static com.codeborne.selenide.Selenide.$;


@Slf4j
public class LogoutForm extends Page {

    @Override
    public boolean isPresent() {
        SelenideElement title = $(TITLE.getLocator());
        return title.isDisplayed() && title.has(Condition.text("Logged out"));
    }

    @Override
    public String getUrlEndpoint() {
        return "";
    }

    @Getter
    @AllArgsConstructor
    public enum LogoutFormElements {
        TITLE(" .mstrPanelTitle "),
        CONTINUE_BUTTON(" .mstrButton[value='Continue'] "),
        ;

        private String locator;

    }

}
