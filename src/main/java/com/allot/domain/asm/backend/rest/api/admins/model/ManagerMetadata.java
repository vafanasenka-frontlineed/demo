package com.allot.domain.asm.backend.rest.api.admins.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class ManagerMetadata {

    @SerializedName("language_id")
    @Builder.Default
    private String languageId = "en-US";

    @SerializedName("time_zone")
    @Builder.Default
    private String timeZone = "Pacific/Funafuti";

}
