package com.allot.cs.http;

import com.allot.core.context.SessionContext;
import com.allot.cs.model.Report;
import com.allot.util.RegexpUtil;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.allot.core.context.SessionContext.CSCookie.*;
import static io.restassured.RestAssured.given;


@Component
@Slf4j
@RequiredArgsConstructor
public class ClearSeeHttpRequest extends ApiService {

    @Value("${app.user}")
    private String defaultUserName;

    @Value("${user.password}")
    private String defaultUserPassword;

    @NonNull
    private final SessionContext sessionContext;

    private static final String RAND_NUM_PATTERN = "mstrConfig.validateRandNum = '(-?[0-9]{18,19})'";

    protected RequestSpecification getCommonSpecWithCookies() {
        return getCommonSpec()
                .cookies(sessionContext.getCookies())
                .queryParam("validateRandNum", sessionContext.getValidateRandNum());
    }

    @Step("Authorize to Clear see")
    public void authorize() {
        getLoginPage();
        Response response = postLoginForm();
        Assertions.assertThat(response.then().extract().body().asString())
                .withFailMessage("Authorization failed for the user : %s ", defaultUserName)
                .contains("Home.");
    }

    private Response getLoginPage() {
        log.info("Get login page");
        Response response = given()
            .spec(getCommonSpec())
                .queryParam("Login", "")
            .when()
                .get();
        response.then().spec(getResponseSpec());
        sessionContext.setCookies(BSET, response);
        sessionContext.setCookies(JSESSIONID, response);
        setValidateRandNumber(response);
        return response;
    }

    private Response postLoginForm() {
        log.info("Post login page with the {} user data", defaultUserName);
        Response response = given()
            .spec(getCommonSpecWithCookies())
                .queryParam("Uid", defaultUserName)
                .queryParam("Pwd", defaultUserPassword)
                .queryParam("ConnMode", 1)
                .queryParam("3054", "Login")
                .queryParam("evt", "3010")
                .queryParam("src", "mstrWeb.3010")
                .queryParam("loginReq", "true")
                .queryParam("Server", "localhost")
                .queryParam("Project", "Allot Real - Dev 2.0")
                .queryParam("Port", "0")
            .when()
                .post();
        response.then().spec(getResponseSpec());
        setValidateRandNumber(response);
        sessionContext.setCookies(BSET, response);
        sessionContext.setCookies(MSTR_AUTH, response);
        sessionContext.setCookies(JSESSIONID, response);
        return response;
    }

    @Step("Get report {report} url")
    public Response openReport(@NonNull final Report report) {
        log.info("Open report by get http page for {} report", report.getTitle());
        return given()
                .spec(getCommonSpecWithCookies())
                .queryParam("evt", "2048001")
                .queryParam("src", "mstrWeb.2048001")
                .queryParam("documentID", report.getDocumentId())
                .when()
                .get();
    }

    private void setValidateRandNumber(@NonNull final Response response) {
        String validateRandNum = RegexpUtil.findExp(RAND_NUM_PATTERN, response.getBody().asString(), 1);
        if (validateRandNum != null) {
            sessionContext.setValidateRandNum(Long.parseLong(validateRandNum));
        }
    }

}
