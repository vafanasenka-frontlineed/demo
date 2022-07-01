package com.allot.core.assertj;

import io.restassured.response.Response;
import org.assertj.core.api.*;
import org.checkerframework.checker.nullness.qual.NonNull;


public class ReportAssert extends AbstractAssert<ReportAssert, Response> {

    public ReportAssert(@NonNull final Response response) {
        super(response, ReportAssert.class);
    }

    public ReportAssert assertResponse() {
        SoftAssertions softy = new SoftAssertions();
        softy.assertThat(actual.getContentType())
                .as("Check response Content Type")
                .isEqualTo("text/html;charset=UTF-8");
        softy.assertThat(actual.getBody().toString())
                .withFailMessage("Response Body is empty")
                .isNotEmpty();
        softy.assertThat(actual.getHeader("Date"))
                .withFailMessage("Response header 'Date' is not present")
                .isNotNull();
        softy.assertAll();
        return this;
    }

    public static ReportAssert assertThat(@NonNull final Response actual) {
        return new ReportAssert(actual);
    }

}
