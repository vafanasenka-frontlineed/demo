
package com.allot.domain.asm.backend.rest.api.account.model.provision;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class QuietTime {

    private Boolean enabled;

    @SerializedName("end_date_time")
    private String endDateTime;

}
