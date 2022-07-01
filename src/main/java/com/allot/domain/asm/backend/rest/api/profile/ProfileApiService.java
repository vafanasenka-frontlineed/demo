package com.allot.domain.asm.backend.rest.api.profile;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.profile.builder.ProfileRequest;
import com.allot.domain.asm.backend.rest.api.profile.model.response.ProfilesResponse;
import com.allot.domain.asm.frontend.model.AccountProfile;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class ProfileApiService extends ApiService {

    @Step("[API] Add profile {profile.id}")
    public void addProfile(AccountProfile profile) {
        log.info("Add new profile {} ", profile.getId());
        addProfileResponse(profile)
                .then()
                .assertThat().statusCode(200);
    }

    public Response addProfileResponse(AccountProfile profile) {
        return given()
                .spec(getCommonSpecWithToken())
                .body(ProfileRequest.addProfile(profile))
            .when()
                .post(ApiService.Endpoints.PROFILES);
    }

    @Step("[API] Get profile list")
    public List<AccountProfile> getProfileList() {
        Response response = given()
                .spec(getCommonSpecWithToken())
            .when()
                .get(ApiService.Endpoints.PROFILES);
        response.then()
                .assertThat().statusCode(200);
        return response.as(ProfilesResponse.class)
                .getProfileItems().stream()
                .map(AccountProfile::map).collect(Collectors.toList());
    }

    @Step("[API] Delete profile {profile.id}")
    public void deleteProfile(AccountProfile profile) {
        String profileId = profile.getId();
        log.info("Delete profile {} ", profileId);
        given()
                .spec(getCommonSpecWithToken())
                .pathParam(PathParameters.PROFILE_ID, profileId)
            .when()
                .delete(Endpoints.PROFILES_PROFILE_ID)
            .then()
                .assertThat().statusCode(204);
    }

}
