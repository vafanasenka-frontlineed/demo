package com.allot.domain.asm.backend.rest.api.session.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class SessionsManagement {

    @SerializedName("captcha_conf")
    @Builder.Default
    private Captcha captchaConf = Captcha.builder().build();

    @SerializedName("access_token_timeout")
    @Builder.Default
    private long accessTokenTimeout = 300;

    @SerializedName("access_token_timeout_seconds")
    @Builder.Default
    private long accessTokenTimeoutSeconds = 600;

    @SerializedName("max_sessions")
    @Builder.Default
    private long maxSessions = 99;

    @SerializedName("password_policy")
    @Builder.Default
    private PasswordPolicy passwordPolicy = PasswordPolicy.builder().build();

    @SerializedName("password_rotation")
    @Builder.Default
    private PasswordRotation passwordRotation = PasswordRotation.builder().build();

    @SerializedName("refresh_token_timeout")
    @Builder.Default
    private long refreshTokenTimeout = 7200;

}
