package com.allot.domain.asm.backend.rest.api.admins;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.admins.builder.ManagerRequest;
import com.allot.domain.asm.backend.rest.api.admins.model.Manager;
import com.allot.domain.asm.frontend.model.*;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.allot.domain.asm.backend.rest.api.ApiService.PathParameters.MANAGER_ID;
import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class ManagerApiService extends ApiService {

    @Step("[API] Add a new manager to the list of managers of the existing account")
    public void addManagerToAccount(User user, UserAccount userAccount) {
        String accountId = userAccount.getAccountId();
        log.info("Add a new manager {} to the account {}", user.getName(), accountId);
        ValidatableResponse response =
                given()
                        .spec(getCommonSpecWithToken())
                        .pathParam(PathParameters.ACCOUNT_ID, userAccount.getAccountId())
                        .body(ManagerRequest.addManagerToAccount(user, accountId))
                    .when()
                        .post(Endpoints.MANAGERS_ACCOUNT)
                    .then()
                        .assertThat().statusCode(200);
        user.setManagerId(response.extract().path(MANAGER_ID));
    }

    @Step("[API] Add a new manager")
    public Manager addManager(User user) {
        log.info("Add a new manager " + user.getName());
        return given()
                .spec(getCommonSpecWithToken())
                .body(ManagerRequest.addManager(user))
            .when()
                .post(Endpoints.MANAGERS)
            .then()
                .assertThat().statusCode(200)
                .and().extract().as(Manager.class);
    }

    @Step("[API] Delete the manager")
    public void deleteManager(User user) {
        log.info("Delete the manager " + user.getName());
        given()
                .spec(getCommonSpecWithToken())
                .pathParam("manager_id", user.getManagerId())
            .when()
                .delete(Endpoints.MANAGERS_MANAGER_ID)
            .then()
                .assertThat().statusCode(204);
    }

}
