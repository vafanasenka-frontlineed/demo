package com.allot.domain.asm.backend.rest.api.account.model.products;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class NetworkSecure {

    @SerializedName("delegated_admin")
    @Builder.Default
    private Boolean delegatedAdmin = true;

    @SerializedName("product_id")
    @Builder.Default
    private String productId = "NetworkSecure";

    @Builder.Default
    private Boolean provisioned = true;

}
