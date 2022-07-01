
package com.allot.domain.asm.backend.rest.api.category.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.*;


@Data
@Builder
public class Category {

    @Builder.Default
    private Boolean enabled = true;

    @SerializedName("isp_category_i18n")
    @Builder.Default
    private List<IspCategoryI18n> ispCategoryI18n = Collections.singletonList(IspCategoryI18n.builder().build());

    @SerializedName("isp_category_id")
    private String ispCategoryId;

    @SerializedName("sm_categories")
    private List<String> smCategories;

}
