package com.allot.domain.asm.backend.rest.api.auth;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.auth.model.*;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;


@Component
public class AuthenticationApiService extends ApiService {

    @Value("${admin.user}")
    private String userName;
    @Value("${admin.password}")
    private String password;

    @Step("[API] Authenticate to ISP to get access token")
    public void authenticate() {
        AuthenticationToken authenticationToken = given()
                .spec(getCommonSpec())
                .relaxedHTTPSValidation()
                .auth().preemptive()
                .basic(userName, password)
                .body(new Object())
            .when()
                .post(Endpoints.LOGIN_ENDPOINT)
            .then()
                .assertThat().statusCode(200)
                .and().extract().as(AuthenticationToken.class);
        asmSessionContext.setAuthenticationToken(authenticationToken);
    }

    @Step("[API] Refresh authentication token")
    public void refreshAuthToken() {
        String refreshToken = asmSessionContext.getAuthenticationToken().getRefreshToken();
        AuthenticationToken authenticationToken = given()
                .spec(getCommonSpec())
                .body(RefreshToken.builder().refreshToken(refreshToken).build())
            .when()
                .post(Endpoints.REFRESH_TOKEN_ENDPOINT)
            .then()
                .assertThat().statusCode(200)
                .and().extract().as(AuthenticationToken.class);
        asmSessionContext.setAuthenticationToken(authenticationToken);
    }

}
