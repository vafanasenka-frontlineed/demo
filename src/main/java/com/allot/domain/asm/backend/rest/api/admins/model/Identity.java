package com.allot.domain.asm.backend.rest.api.admins.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;


@Data
@Builder
public class Identity {

    @Builder.Default
    private Boolean blocked = false;

    @SerializedName("identity_instances")
    private List<IdentityInstance> identityInstances;
    private String name;

}
