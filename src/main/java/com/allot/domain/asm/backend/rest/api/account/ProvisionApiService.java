package com.allot.domain.asm.backend.rest.api.account;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.account.builder.ProvisionRequest;
import com.allot.domain.asm.frontend.model.*;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class ProvisionApiService extends ApiService {

    @Step("[API] Provision a new account with the device")
    public void provisionAccount(UserAccount userAccount, UserDevice userDevice) {
        String accountId = userAccount.getAccountId();
        log.info("Provision a new account : {} ", accountId);
        ValidatableResponse response = given()
                .spec(getCommonSpecWithToken())
                .body(ProvisionRequest.provision(userAccount, userDevice))
            .when()
                .post(Endpoints.PROVISION)
            .then()
                .assertThat().statusCode(200);
        userDevice.setAccountId(response.extract().jsonPath().get("devices[0].account_id"));
        userDevice.setDeviceId(response.extract().jsonPath().get("devices[0].device_id"));
    }

}
