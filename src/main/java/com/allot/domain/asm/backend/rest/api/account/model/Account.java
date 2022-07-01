package com.allot.domain.asm.backend.rest.api.account.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class Account {

    @SerializedName("account_id")
    private String accountId;

    @SerializedName("account_type")
    private String accountType;

    @Builder.Default
    private Products products = Products.builder().build();

    @Builder.Default
    private Profiles profiles = Profiles.builder().build();

    @SerializedName("services_provisioned")
    private ServicesProvisioned servicesProvisioned;

}
