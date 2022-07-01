package com.allot.domain.asm.backend.rest.api.account;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.account.builder.AccountRequest;
import com.allot.domain.asm.backend.rest.api.account.model.response.AccountsResponse;
import com.allot.domain.asm.frontend.model.*;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import static com.allot.domain.asm.backend.rest.api.ApiService.PathParameters.*;
import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class AccountApiService extends ApiService {

    @Step("[API] Get a list of all provisioned accounts")
    public List<UserAccount> getAccountsList() {
        log.info("Retrieves a list of all provisioned accounts");
        ValidatableResponse response =
                given()
                    .spec(getCommonSpecWithToken())
                .when()
                    .get(Endpoints.ACCOUNTS)
                .then()
                    .assertThat().statusCode(200);
        return response.extract().as(AccountsResponse.class)
                .getItems().stream()
                .map(UserAccount::map).collect(Collectors.toList());
    }

    @Step("[API] Add a new account")
    public void addAccount(UserAccount userAccount) {
        log.info("Add a new account : {} ", userAccount.getAccountId());
        given()
            .spec(getCommonSpecWithToken())
            .body(AccountRequest.addAccount(userAccount))
        .when()
            .post(Endpoints.ACCOUNTS)
        .then()
            .assertThat().statusCode(200);
    }

    @Step("[API] Delete the existing account")
    public void deleteAccount(UserAccount userAccount) {
        String accountId = userAccount.getAccountId();
        log.info("Delete the existing account : {}", accountId);
        given()
            .spec(getCommonSpecWithToken())
            .pathParam(PathParameters.ACCOUNT_ID, accountId)
        .when()
            .delete(Endpoints.ACCOUNTS_ACCOUNT_ID)
        .then()
            .assertThat().statusCode(204);
    }

    @Step("[API] Update account device name to {deviceName}")
    public void updateAccountDeviceName(UserDevice device, String deviceName) {
        String accountId = device.getAccountId();
        log.info("Update account device name from {} to {}", device.getDescription(), deviceName);
        given()
            .spec(getCommonSpecWithToken())
            .pathParam(ACCOUNT_ID, accountId)
            .pathParam(DEVICE_ID, device.getDeviceId())
        .when()
            .body(String.format("{\"description\": \"%s\"}", deviceName))
            .patch(Endpoints.ACCOUNTS_DEVICES)
        .then()
            .assertThat().statusCode(204);
    }

    @Step("[API] Reassign device from account1 to account2")
    public void reassignDeviceToAnotherAccount(UserDevice device, UserAccount targetAccount) {
        String targetAccountId = targetAccount.getAccountId();
        log.info("Reassign device {} from existing account to another account {}",
                device.getDescription(), targetAccountId);
        given()
            .spec(getCommonSpecWithToken())
            .pathParam(DEVICE_ID, device.getDeviceId())
        .when()
            .body(String.format("{\"account_id\": \"%s\"}", targetAccountId))
            .post(Endpoints.DEVICES_REASSIGN)
        .then()
            .assertThat().statusCode(200);
    }

}
