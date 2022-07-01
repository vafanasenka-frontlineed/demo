
package com.allot.domain.asm.backend.rest.api.profile.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class ProfileI18n {

    @SerializedName("language_id")
    @Builder.Default
    private String languageId = "en-US";

    @SerializedName("profile_name")
    private String profileName;

    @SerializedName("profile_description")
    private String profileDescription;

}
