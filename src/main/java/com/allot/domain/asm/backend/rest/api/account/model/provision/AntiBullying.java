
package com.allot.domain.asm.backend.rest.api.account.model.provision;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class AntiBullying {

    @SerializedName("enabled")
    private Boolean enabled;

}
