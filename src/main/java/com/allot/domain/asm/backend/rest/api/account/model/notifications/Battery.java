
package com.allot.domain.asm.backend.rest.api.account.model.notifications;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class Battery {

    @SerializedName("battery_low")
    @Builder.Default
    private Boolean batteryLow = false;

}
