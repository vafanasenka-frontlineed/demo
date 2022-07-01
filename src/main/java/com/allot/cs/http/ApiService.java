package com.allot.cs.http;

import com.allot.cs.step.LoginSteps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.*;
import io.restassured.specification.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.allot.core.context.SessionContext.CSCookie.*;
import static com.allot.util.HttpUrlBuilder.HTTPSchema.HTTP;


@Component
public class ApiService {

    @Autowired
    private LoginSteps loginSteps;

    protected RequestSpecification getCommonSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(loginSteps.getBaseUrl(HTTP))
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like "
                        + "Gecko) Chrome/84.0.4147.89 Safari/537.36")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,"
                        + "*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .addHeader("Host", loginSteps.getHost())
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "en-US,en;q=0.9")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("Connection", "keep-alive")
                .addFilter(new AllureRestAssured())
                .build();
    }

    protected ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectCookie(JSESSIONID.getName())
                .expectCookie(BSET.getName())
                .build();
    }

}
