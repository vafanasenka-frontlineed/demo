
package com.allot.domain.asm.backend.rest.api.category.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class IspCategoryI18n {

    @SerializedName("isp_category_name")
    @Builder.Default
    private String ispCategoryName = "en-US";

    @SerializedName("language_id")
    @Builder.Default
    private String languageId = "English (US)";

}
