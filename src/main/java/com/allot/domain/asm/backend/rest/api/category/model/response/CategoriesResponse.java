package com.allot.domain.asm.backend.rest.api.category.model.response;


import com.allot.domain.asm.backend.rest.api.category.model.Category;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Data
@Builder
public class CategoriesResponse {

    @SerializedName("items")
    private List<Category> categoryItems;

}
