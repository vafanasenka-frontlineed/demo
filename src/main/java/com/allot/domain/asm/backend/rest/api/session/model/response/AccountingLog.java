
package com.allot.domain.asm.backend.rest.api.session.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;


@Data
@Builder
public class AccountingLog {

    private Boolean encryption;

    @SerializedName("encryption_fields")
    private List<String> encryptionFields;

    @SerializedName("encryption_key")
    private String encryptionKey;

    @SerializedName("log_conf_refresh")
    private Long logConfRefresh;

    @SerializedName("logs_rotation")
    private Long logsRotation;

}
