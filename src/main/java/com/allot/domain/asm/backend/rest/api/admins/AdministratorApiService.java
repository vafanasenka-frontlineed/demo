package com.allot.domain.asm.backend.rest.api.admins;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.admins.builder.AdministratorRequest;
import com.allot.domain.asm.frontend.model.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class AdministratorApiService extends ApiService {

    @Step("[API] Add new administrator to the ISP")
    public User addAdministrator(User user) {
        ValidatableResponse response =
                given()
                        .spec(getCommonSpecWithToken())
                        .body(AdministratorRequest.addAdministrator(user))
                    .when()
                        .post(Endpoints.ADMINS)
                    .then()
                        .assertThat().statusCode(200);
        String managerId = response.extract().path("manager_id");
        log.info("New Administrator is successfully added with managerId : " + managerId);
        user.setManagerId(managerId);
        user.setAccountId(response.extract().path("account_id"));
        return user;
    }

    @Step("[API] Delete administrator from the ISP")
    public void deleteAdministrator(User user) {
        log.info("Delete administrator from the ISP " + user.getName());
        given()
                .spec(getCommonSpecWithToken())
                .pathParam(PathParameters.MANAGER_ID, user.getManagerId())
            .when()
                .delete(Endpoints.ADMINS_ADMIN_ID)
            .then()
                .assertThat().statusCode(204);
    }

}
