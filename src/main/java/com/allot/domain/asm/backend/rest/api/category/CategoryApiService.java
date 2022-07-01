package com.allot.domain.asm.backend.rest.api.category;

import com.allot.domain.asm.backend.rest.api.ApiService;
import com.allot.domain.asm.backend.rest.api.category.builder.CategoryRequest;
import com.allot.domain.asm.backend.rest.api.category.model.response.CategoriesResponse;
import com.allot.domain.asm.frontend.model.AccountCategory;
import io.qameta.allure.Step;
import io.restassured.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


@Slf4j
@Component
public class CategoryApiService extends ApiService {

    @Step("[API] Add category {category.id}")
    public void addCategory(AccountCategory category) {
        log.info("Add new category {} ", category.getId());
        addCategoryResponse(category)
                .then()
                .assertThat().statusCode(200);
    }

    public Response addCategoryResponse(AccountCategory category) {
        return given()
                .spec(getCommonSpecWithToken())
                .body(CategoryRequest.addCategory(category))
            .when()
                .post(ApiService.Endpoints.ISP_CATEGORIES);
    }

    @Step("[API] Add category {category.id}")
    public List<AccountCategory> getCategoryList() {
        ValidatableResponse response = given()
            .spec(getCommonSpecWithToken())
        .when()
            .get(ApiService.Endpoints.ISP_CATEGORIES)
        .then()
            .assertThat().statusCode(200);
        return response.extract().as(CategoriesResponse.class)
                .getCategoryItems().stream()
                .map(AccountCategory::map).collect(Collectors.toList());
    }

    @Step("[API] Add category {category.id}")
    public void deleteCategory(AccountCategory category) {
        log.info("Add new category {} ", category.getId());
        given()
            .spec(getCommonSpecWithToken())
            .pathParam(PathParameters.CATEGORY_ID, category.getId())
        .when()
            .delete(ApiService.Endpoints.ISP_CATEGORIES_CATEGORY_ID)
        .then()
            .assertThat().statusCode(204);
    }

}
