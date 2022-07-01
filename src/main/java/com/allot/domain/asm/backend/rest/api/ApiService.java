package com.allot.domain.asm.backend.rest.api;

import com.allot.util.ApiKeyGenerator;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.*;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

import static com.allot.domain.asm.backend.rest.api.ApiService.PathParameters.*;


@Getter
@Setter
public abstract class ApiService {

    @Autowired
    protected ASMSessionContext asmSessionContext;
    @Autowired
    protected ApiKeyGenerator apiKeyGenerator;
    @Value("${admin.base.url}")
    protected String baseUrl;
    @Value("${admin.base.path}")
    protected String basePath;

    protected RequestSpecification getCommonSpecWithApiKey() {
        return getCommonSpec()
                .relaxedHTTPSValidation()
                .header("X-API-KEY", apiKeyGenerator.generate());
    }

    protected RequestSpecification getCommonSpecWithToken() {
        return getCommonSpec()
                .relaxedHTTPSValidation()
                .auth()
                .oauth2(asmSessionContext.getAuthenticationToken().getAccessToken());
    }

    protected RequestSpecification getCommonSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .addHeader("Accept-Charset", "UTF-8")
                .addFilter(new AllureRestAssured())
                .setConfig(RestAssuredConfig.config()
                        .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .build();
    }

    public interface Endpoints {

        String ACCOUNTS = "accounts";
        String ACCOUNTS_ACCOUNT_ID = build(ACCOUNTS, pathParameter(ACCOUNT_ID));
        String ACCOUNTS_DEVICES = build(ACCOUNTS, pathParameter(ACCOUNT_ID), "devices", pathParameter(DEVICE_ID));
        String ADMINS = "administrators";
        String ADMINS_ADMIN_ID = build(ADMINS, pathParameter(MANAGER_ID));
        String ISP_CATEGORIES = "isp-categories";
        String ISP_CATEGORIES_CATEGORY_ID = build(ISP_CATEGORIES, pathParameter(CATEGORY_ID));
        String DEVICES = "devices";
        String DEVICES_REASSIGN = build(DEVICES, pathParameter(DEVICE_ID), "reassign");
        String IDENTITY = "identity";
        String LOGIN_ENDPOINT = build(IDENTITY, "login");
        String MANAGERS = "managers";
        String MANAGERS_ACCOUNT = build(ACCOUNTS, pathParameter(ACCOUNT_ID), MANAGERS);
        String MANAGERS_MANAGER_ID = build(MANAGERS, pathParameter(MANAGER_ID));
        String PROVISION = "provision";
        String PROFILES = "profiles";
        String PROFILES_PROFILE_ID =  build(PROFILES, pathParameter(PROFILE_ID));
        String REFRESH_TOKEN_ENDPOINT = build(IDENTITY, "refreshtoken");
        String SESSION_CONFIGURATION = build(IDENTITY, "session", "configuration");

        static String build(CharSequence... pathElements) {
            StringJoiner joiner = new StringJoiner("/", "/", "");
            Arrays.asList(pathElements).forEach(joiner::add);
            return joiner.toString();
        }

        static String pathParameter(String parameterValue) {
            return String.format("{%s}", parameterValue);
        }

    }

    public interface PathParameters {

        String ACCOUNT_ID = "account_id";
        String MANAGER_ID = "manager_id";
        String DEVICE_ID = "device_id";
        String CATEGORY_ID = "isp_category_id";
        String PROFILE_ID = "profile_id";
        String TYPE = "type";

    }

}
