package com.allot.domain.asm.backend.rest.api.session.builder;

import com.allot.domain.asm.backend.rest.api.session.model.*;


public class SessionConfigurationRequest {

    public static SessionConfiguration setDefault() {
        return SessionConfiguration.builder().build();
    }

    public static SessionConfiguration updateMaxSessions(long maxSessions) {
        var maxSessionsManagement = SessionsManagement.builder()
                .maxSessions(maxSessions)
                .build();
        return SessionConfiguration.builder()
                .sessionsManagement(maxSessionsManagement)
                .build();
    }

    public static SessionConfiguration updateRefreshTokenTimeout(long maxSessions) {
        var refreshTokenManagement = SessionsManagement.builder()
                .refreshTokenTimeout(maxSessions)
                .build();
        return SessionConfiguration.builder()
                .sessionsManagement(refreshTokenManagement)
                .build();
    }

    public static SessionConfiguration updateHistoryCheck(long passwordHistoryCheck) {
        var passwordRotationHistory = PasswordRotation.builder()
                .historyCheckDepth(passwordHistoryCheck)
                .build();
        var passwordRotationManagement = SessionsManagement.builder()
                .passwordRotation(passwordRotationHistory)
                .build();
        return SessionConfiguration.builder()
                .sessionsManagement(passwordRotationManagement)
                .build();
    }

    public static SessionConfiguration updatePasswordPolicy(long lockTime, long maxIncorrectPasswordAttempts) {
        var passwordPolicyLockTimeAndIncorrectAttempts = PasswordPolicy.builder()
                .lockTime(lockTime)
                .maxIncorrectPasswordAttempts(maxIncorrectPasswordAttempts)
                .build();
        var passwordPolicyManagement = SessionsManagement.builder()
                .passwordPolicy(passwordPolicyLockTimeAndIncorrectAttempts)
                .build();
        return SessionConfiguration.builder()
                .sessionsManagement(passwordPolicyManagement)
                .build();
    }

    public static CaptchaConf captcha(boolean isEnabled) {
        var captcha = Captcha.builder()
                .enabled(isEnabled)
                .build();
        return CaptchaConf.builder().captcha(captcha).build();
    }

}
