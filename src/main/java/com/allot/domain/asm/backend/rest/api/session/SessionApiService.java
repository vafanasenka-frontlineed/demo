package com.allot.domain.asm.backend.rest.api.session;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.backend.rest.api.session.model.SessionConfiguration;
import com.allot.domain.asm.backend.rest.api.session.model.response.SessionConfigurationResponse;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class SessionApiService extends ApiService {

    @Step("[API] Get Password policy session configuration")
    public SessionConfigurationResponse getSessionConfiguration() {
        log.info("Get Password policy session configuration");
        return given()
                .spec(getCommonSpecWithToken())
            .when()
                .get(Endpoints.SESSION_CONFIGURATION)
            .then()
                .assertThat().statusCode(200)
                .and().extract().as(SessionConfigurationResponse.class);
    }

    @Step("[API] Set Password policy configuration")
    public void setSessionConfiguration(SessionConfiguration sessionConfiguration) {
        log.info("Configure Password policy");
        given()
                .spec(getCommonSpecWithToken())
                .body(sessionConfiguration)
            .when()
                .put(Endpoints.SESSION_CONFIGURATION)
            .then()
                .assertThat().statusCode(200);
    }

    @Step("[API] Set capture enabled : {isEnable} ")
    public void configureCapture(boolean isEnable) {
        log.info("Set capture enabled to [{}]", isEnable);
        given()
                .spec(getCommonSpecWithApiKey())
                .param(PathParameters.TYPE, "isp")
                .body(SessionConfigurationRequest.captcha(isEnable))
                .when()
                .patch(Endpoints.SESSION_CONFIGURATION)
                .then()
                .assertThat().statusCode(200);
    }

}
