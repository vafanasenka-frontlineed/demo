package com.allot.domain.asm.backend.rest.api.account.model.products;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class EndpointSecure {

    @SerializedName("product_id")
    @Builder.Default
    private String productId = "EndpointSecure";

    @Builder.Default
    private Boolean provisioned = true;

}
