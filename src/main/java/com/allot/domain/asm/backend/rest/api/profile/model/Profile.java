
package com.allot.domain.asm.backend.rest.api.profile.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Data
@Builder
public class Profile {

    @SerializedName("profile_definition_id")
    private String profileDefinitionId;

    @SerializedName("profile_i18n")
    private List<ProfileI18n> profileI18n;

    @SerializedName("services_configuration")
    private ServicesConfiguration servicesConfiguration;

}
