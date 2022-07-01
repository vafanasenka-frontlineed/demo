package com.allot.core.assertj;

import com.allot.core.driver.Page;
import org.assertj.core.api.AbstractAssert;


public class PageAssert extends AbstractAssert<PageAssert, Page> {

    public PageAssert(Page actualPage) {
        super(actualPage, PageAssert.class);
    }

    public PageAssert isPageDisplayed() {
        if (!actual.isPresent()) {
            failWithMessage("Page is not displayed %s", actual.getUrlEndpoint());
        }
        return this;
    }

    public PageAssert isPageNotDisplayed() {
        if (actual.isPresent()) {
            failWithMessage("Page is displayed %s", actual.getUrlEndpoint());
        }
        return this;
    }

    public static PageAssert assertThat(Page actual) {
        return new PageAssert(actual);
    }

}
