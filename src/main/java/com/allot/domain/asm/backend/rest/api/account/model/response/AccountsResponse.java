package com.allot.domain.asm.backend.rest.api.account.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;


@Data
@Builder
public class AccountsResponse {

    private List<Item> items;

    @SerializedName("next_page")
    private String nextPage;

}
