package com.allot.domain.asm.backend.rest.api.auth.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class RefreshToken {

    @SerializedName("refresh_token")
    private String refreshToken;

}
