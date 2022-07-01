package com.allot.secure.e2e;

import com.allot.domain.asm.backend.rest.api.session.SessionApiService;
import com.allot.secure.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CaptchaTest extends BaseTest {

    @Autowired
    protected SessionApiService sessionService;

    @BeforeEach
    protected void authenticate() {
        authService.authenticate();
    }

    @Test
    public void disableCaptcha() {
        sessionService.configureCapture(false);
        Assertions.assertThat(sessionService.getSessionConfiguration().getCaptcha().getEnabled())
                .as("Verify that captcha is disabled")
                .isFalse();
    }

}
