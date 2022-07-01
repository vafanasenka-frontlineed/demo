package com.allot.domain.asm.backend.rest.api.admins.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;


@Data
@Builder
public class Administrator {

    private Identity identity;

    @SerializedName("manager_metadata")
    @Builder.Default
    private ManagerMetadata managerMetadata = ManagerMetadata.builder().build();

    private List<String> roles;

}
