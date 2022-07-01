package com.allot.cs.page.element;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.allot.cs.page.element.Dropdown.DropdownElement.ITEMS;
import static com.allot.cs.page.element.Dropdown.DropdownElement.SELECTED_VALUE;


@AllArgsConstructor
public class Dropdown {

    @AllArgsConstructor
    public enum DropdownElement {

        SELECTED_VALUE("button.Container_btn"),
        ITEMS("a.Container_drop_item"),
        ;

        @Getter
        private String locator;
    }

    private SelenideElement container;

    public String getCurrentValue() {
        return container.$(SELECTED_VALUE.getLocator()).text();
    }

    public void setValue(String value) {
        if (!getCurrentValue().equals(value)) {
            container.$(SELECTED_VALUE.getLocator()).click();
            container.$$(ITEMS.getLocator())
                    .shouldBe(CollectionCondition.sizeGreaterThan(0))
                    .stream()
                    .filter(item -> item.text().equals(value))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Dropdown value '%s' is not found", value)))
                    .click();
        }
    }

}
