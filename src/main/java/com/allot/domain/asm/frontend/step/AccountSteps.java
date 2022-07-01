package com.allot.domain.asm.frontend.step;

import com.allot.domain.asm.frontend.model.User;
import com.allot.domain.asm.frontend.page.acc.*;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AccountSteps {

    @Value("${account.base.url}")
    private String baseUrl;

    @Step("Login to ASM account")
    public HomePage loginToAccount(User user) {
        return loginToAccount(user.getName(), user.getPassword());
    }

    @Step("Login to ASM account")
    public HomePage loginToAccount(String user, String password) {
        log.info("Login to ASM account with the user {} ", user);
        return Selenide.open(baseUrl, LoginPage.class)
                .login(user, password);
    }

}
