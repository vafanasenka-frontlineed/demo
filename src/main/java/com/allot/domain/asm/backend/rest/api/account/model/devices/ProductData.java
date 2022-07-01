package com.allot.domain.asm.backend.rest.api.account.model.devices;

import com.allot.domain.asm.backend.rest.api.account.model.products.*;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Builder
@Data
public class ProductData {

    @SerializedName("EndpointSecure")
    @Builder.Default
    private EndpointSecure endpointSecure = EndpointSecure.builder().build();

    @SerializedName("HomeSecure")
    @Builder.Default
    private HomeSecure homeSecure = HomeSecure.builder().build();

    @SerializedName("NetworkSecure")
    @Builder.Default
    private NetworkSecure networkSecure = NetworkSecure.builder().build();

}
