package com.allot.domain.asm.backend.rest.api.account.model.products;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class HomeSecure {

    @SerializedName("product_id")
    @Builder.Default
    private String productId = "HomeSecure";

    @Builder.Default
    private Boolean provisioned = true;

}
