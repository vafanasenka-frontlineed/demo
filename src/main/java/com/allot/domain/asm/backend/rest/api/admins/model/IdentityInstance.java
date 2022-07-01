package com.allot.domain.asm.backend.rest.api.admins.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class IdentityInstance {

    @SerializedName("identity_instance_type")
    @Builder.Default
    private String identityInstanceType = "IdentityInstanceDatabase";

    @SerializedName("identity_provider_type")
    @Builder.Default
    private String identityProviderType = "IdentityProviderDatabase";

    private String password;

    @SerializedName("user_id")
    private String userId;

}
