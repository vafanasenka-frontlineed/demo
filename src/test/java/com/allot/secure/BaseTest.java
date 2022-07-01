package com.allot.secure;

import com.allot.core.driver.selenide.extensions.AllureExtension;
import com.allot.core.testdata.ITestDataProvider;
import com.allot.domain.asm.backend.rest.api.auth.AuthenticationApiService;
import com.allot.spring.asm.ASMSpringConfig;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SoftAssertionsExtension.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = ASMSpringConfig.class)
public class BaseTest {

    @Autowired
    protected ITestDataProvider testDataProvider;

    @Autowired
    @RegisterExtension
    protected AllureExtension allureExtension;

    @Autowired
    protected AuthenticationApiService authService;

}
