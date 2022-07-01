
package com.allot.domain.asm.backend.rest.api.account.model.notifications;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class InternetAvailability {

    @SerializedName("long_disconnectivity")
    @Builder.Default
    private Boolean longDisconnectivity = false;

    @SerializedName("short_disconnectivity")
    @Builder.Default
    private Boolean shortDisconnectivity = false;

}
