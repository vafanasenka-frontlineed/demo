package com.allot.cs;

import com.allot.core.driver.selenide.extensions.*;
import com.allot.core.testdata.ITestDataProvider;
import com.allot.spring.cs.ClearSeeSpringConfig;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SoftAssertionsExtension.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = ClearSeeSpringConfig.class)
public class CSBaseTest {

    @Autowired
    protected ITestDataProvider testDataProvider;

    @Autowired
    @RegisterExtension
    protected AllureExtension allureExtension;

    @Autowired
    @RegisterExtension
    protected SelenideExtension selenideExtension;

}
