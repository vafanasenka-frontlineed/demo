package com.allot.domain.asm.backend.rest.api.account.model;

import com.allot.domain.asm.backend.rest.api.account.model.products.*;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class Products {

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
