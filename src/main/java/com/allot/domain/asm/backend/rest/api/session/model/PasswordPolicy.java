package com.allot.domain.asm.backend.rest.api.session.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class PasswordPolicy {

    @Builder.Default
    @SerializedName("lock_time")
    private long lockTime = 30;

    @Builder.Default
    @SerializedName("max_incorrect_password_attempts")
    private long maxIncorrectPasswordAttempts = 100;

}
