package com.allot.secure.e2e.provision;

import com.allot.core.testdata.TestDataSource;
import com.allot.domain.asm.backend.rest.api.category.CategoryApiService;
import com.allot.domain.asm.frontend.model.AccountCategory;
import com.allot.secure.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;


class AddCategoryTest extends BaseTest {

    @Autowired
    private CategoryApiService categoryApiService;
    private AccountCategory category;

    @BeforeEach
    protected void authenticate() {
        category = testDataProvider.getData(TestDataSource.CATEGORY, "Provision_category");
        authService.authenticate();
    }

    @Test
    @TmsLink("C16565")
    @Description("Add Categories via API")
    @Feature(value = "Keepers: ISP Service Configuration › Provisioning")
    void addCategoryTest(SoftAssertions softy) {
        Response response = categoryApiService.addCategoryResponse(category);
        softy.assertThat(response)
                .extracting(ResponseOptions::getStatusCode)
                .as("Check status code OK")
                .isEqualTo(200);
        softy.assertThat(categoryApiService.getCategoryList())
                .as("Verify actual categories list contains added category")
                .contains(category);
        response = categoryApiService.addCategoryResponse(category);
        softy.assertThat(response)
                .extracting(ResponseOptions::getStatusCode)
                .as("Check status code is NOT OK for adding existing category")
                .isEqualTo(409);
        softy.assertAll();
    }

    @AfterEach
    protected void deleteCategory() {
        categoryApiService.deleteCategory(category);
    }

}
