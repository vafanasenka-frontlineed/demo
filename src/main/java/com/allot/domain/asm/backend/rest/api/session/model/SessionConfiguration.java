package com.allot.domain.asm.backend.rest.api.session.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class SessionConfiguration {

    @SerializedName("sessions_management")
    @Builder.Default
    private SessionsManagement sessionsManagement = SessionsManagement.builder().build();

}
