package com.allot.domain.asm.backend.rest.api.admins.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class Manager {

    @SerializedName("account_id")
    private String accountId;

    private Identity identity;

    @SerializedName("manager_metadata")
    @Builder.Default
    private ManagerMetadata managerMetadata = ManagerMetadata.builder().build();

}
