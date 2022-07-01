package com.allot.domain.asm.frontend.page;

import com.codeborne.selenide.*;

import static com.allot.domain.asm.frontend.page.Tab.TabPageElement.OPEN_ICON;


public interface Tab {

    static <T extends Tab> T open(Class<T> clazz, int tabIndex) {
        Selenide.$(OPEN_ICON.getLocator(), tabIndex)
                .waitUntil(Condition.visible, Configuration.timeout)
                .scrollTo()
                .click();
        return Selenide.page(clazz);
    }

    boolean isActive();


    public enum TabPageElement {

        OPEN_ICON(" a[role='tablist'] "),
        ;

        private String locator;

        TabPageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
