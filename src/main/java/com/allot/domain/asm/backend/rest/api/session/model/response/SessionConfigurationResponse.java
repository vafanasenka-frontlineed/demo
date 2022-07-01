
package com.allot.domain.asm.backend.rest.api.session.model.response;

import com.allot.domain.asm.backend.rest.api.session.model.*;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class SessionConfigurationResponse {

    @SerializedName("accounting_log")
    private AccountingLog accountingLog;

    @SerializedName("captcha_conf")
    private Captcha captcha;

    @SerializedName("sessions_management")
    private SessionsManagement sessionsManagement;

}
