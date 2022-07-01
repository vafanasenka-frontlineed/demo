package com.allot.domain.asm.backend.rest.api.account.model.response;

import com.allot.domain.asm.backend.rest.api.account.model.Products;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class Item {

    @SerializedName("account_id")
    private String accountId;

    @SerializedName("account_type")
    private String accountType;

    private Products products;

}
