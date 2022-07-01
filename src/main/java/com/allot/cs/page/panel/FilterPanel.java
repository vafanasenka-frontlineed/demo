package com.allot.cs.page.panel;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.allot.cs.page.panel.FilterPanel.FilterPanelElement.FILTERS_LIST;
import static com.allot.cs.page.panel.FilterPanel.FilterPanelElement.RESOLUTION_VALUE;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class FilterPanel {

    @Getter
    @AllArgsConstructor
    public enum FilterPanelElement {

        RESOLUTION_VALUE(" .fp-titlebar "),
        FILTER_PANEL(" .mstrmojo-VIFilterPanel "),
        FILTERS_LIST(FILTER_PANEL.getLocator() + "  .mstrmojo-VIPanel "),
        ;

        private final String locator;

    }

    public String getResolution() {
        return $(RESOLUTION_VALUE.getLocator()).getText().trim();
    }

    public List<String> getActiveFilters() {
        return $$(FILTERS_LIST.getLocator()).stream()
                .filter(SelenideElement::isDisplayed)
                .map(filterElement -> filterElement.$(".title-text").getText().trim())
                .collect(Collectors.toList());
    }

}
