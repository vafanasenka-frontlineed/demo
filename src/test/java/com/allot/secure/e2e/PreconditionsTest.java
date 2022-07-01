package com.allot.secure.e2e;

import com.allot.core.testdata.TestDataSource;
import com.allot.domain.asm.backend.rest.api.category.CategoryApiService;
import com.allot.domain.asm.backend.rest.api.profile.ProfileApiService;
import com.allot.domain.asm.backend.rest.api.session.SessionApiService;
import com.allot.domain.asm.frontend.model.*;
import com.allot.secure.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


class PreconditionsTest extends BaseTest {

    @Autowired
    protected SessionApiService sessionService;
    @Autowired
    protected ProfileApiService profileService;
    @Autowired
    protected CategoryApiService categoryService;
    List<AccountCategory> categoryList;
    List<AccountProfile> profileList;

    @BeforeEach
    protected void authenticate() {
        authService.authenticate();
        categoryList = testDataProvider.getDataList(TestDataSource.DEFAULT_CATEGORY);
        profileList = testDataProvider.getDataList(TestDataSource.DEFAULT_PROFILE);
    }

    @Test
    void provisionDefaultCategoriesAndProfiles(SoftAssertions softy) {
        categoryList.forEach(category -> categoryService.addCategoryResponse(category));
        softy.assertThat(categoryService.getCategoryList())
                .as("Verify all default categories added")
                .containsAll(categoryList);
        profileList.forEach(profile -> profileService.addProfileResponse(profile));
        softy.assertThat(profileService.getProfileList())
                .as("Verify all default profiles added")
                .containsAll(profileList);
        softy.assertAll();
    }

}
