package com.allot.domain.asm.backend.rest.api.account.model.devices;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Builder
@Data
public class Device {

    @SerializedName("account_id")
    private String accountId;

    private String description;

    private String platform;

    @Builder.Default
    @SerializedName("product_data")
    private ProductData productData = ProductData.builder().build();

    private String type;

}
