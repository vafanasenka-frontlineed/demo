package com.allot.domain.asm.backend.rest.api.session.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class PasswordRotation {

    @SerializedName("history_check_depth")
    @Builder.Default
    private long historyCheckDepth = 0;

    @SerializedName("maximum_elapsed_time_without_change")
    @Builder.Default
    private long maximumElapsedTimeWithoutChange = 0;

    @SerializedName("minimum_elapsed_time_to_change")
    @Builder.Default
    private long minimumElapsedTimeToChange = 30;

    @SerializedName("rotate_on_first_login")
    @Builder.Default
    private Boolean rotateOnFirstLogin = false;

}
