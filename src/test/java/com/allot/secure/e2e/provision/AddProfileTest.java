package com.allot.secure.e2e.provision;

import com.allot.core.testdata.TestDataSource;
import com.allot.domain.asm.backend.rest.api.profile.ProfileApiService;
import com.allot.domain.asm.frontend.model.AccountProfile;
import com.allot.secure.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;


class AddProfileTest extends BaseTest {

    @Autowired
    private ProfileApiService profileService;
    private AccountProfile profile;

    @BeforeEach
    protected void authenticate() {
        profile = testDataProvider.getData(TestDataSource.PROFILE, "Provision_profile");
        authService.authenticate();
    }

    @Test
    @TmsLink("C16566")
    @Description("Add Profiles via API")
    @Feature(value = "Keepers: ISP Service Configuration › Provisioning")
    void addProfileTest(SoftAssertions softy) {
        Response response = profileService.addProfileResponse(profile);
        softy.assertThat(response)
                .extracting(ResponseOptions::getStatusCode)
                .as("Check status code OK")
                .isEqualTo(200);
        softy.assertThat(profileService.getProfileList())
                .as("Verify actual profiles list contains added profile")
                .contains(profile);
        response = profileService.addProfileResponse(profile);
        softy.assertThat(response)
                .extracting(ResponseOptions::getStatusCode)
                .as("Check status code is NOT OK for adding existing profile")
                .isEqualTo(409);
        softy.assertAll();
    }

    @AfterEach
    protected void deleteProfile() {
        profileService.deleteProfile(profile);
    }

}
