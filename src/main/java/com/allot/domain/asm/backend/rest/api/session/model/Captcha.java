package com.allot.domain.asm.backend.rest.api.session.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class Captcha {

    @Builder.Default
    private Boolean enabled = false;

    @SerializedName("expiration_seconds")
    @Builder.Default
    private long expirationSeconds = 60;

}
