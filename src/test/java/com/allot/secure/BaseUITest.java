package com.allot.secure;

import com.allot.core.driver.selenide.config.DriverConfig;
import com.allot.core.driver.selenide.extensions.SelenideExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(SelenideExtension.class)
public class BaseUITest extends BaseTest {

    @Autowired
    protected DriverConfig driverConfig;

    @BeforeAll
    protected void setDriverConfig() {
        driverConfig.updateSelenideConfiguration();
    }

}
