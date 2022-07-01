package com.allot.secure.e2e.password.policy.admin.extension;

import com.allot.core.testdata.*;
import com.allot.domain.asm.backend.rest.api.admins.AdministratorApiService;
import com.allot.domain.asm.backend.rest.api.session.SessionApiService;
import com.allot.domain.asm.backend.rest.api.session.builder.SessionConfigurationRequest;
import com.allot.domain.asm.frontend.model.User;
import com.allot.domain.asm.frontend.step.AdminSteps;
import com.allot.secure.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BaseAdministratorTest extends BaseTest {

    @Autowired
    protected AdministratorApiService adminService;
    @Autowired
    protected SessionApiService sessionService;
    @Autowired
    protected ITestDataProvider testDataProvider;
    @Autowired
    protected AdminSteps adminUiSteps;
    protected User administrator;

    @BeforeAll
    protected void preconditions() {
        administrator = testDataProvider.getData(TestDataSource.USER, "Password_policy_admin");
        authService.authenticate();
        administrator = adminService.addAdministrator(administrator);
        log.info("Set default policy configuration");
        sessionService.setSessionConfiguration(SessionConfigurationRequest.setDefault());
    }

    @AfterAll
    protected void cleanUp() {
        authService.refreshAuthToken();
        log.info("Revert Password Policy configuration to the default");
        sessionService.setSessionConfiguration(SessionConfigurationRequest.setDefault());
        log.info("Delete created administrator user {} ", administrator.getName());
        adminService.deleteAdministrator(administrator);
    }

}
