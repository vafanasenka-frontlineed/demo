package com.allot.domain.asm.backend.rest.api.account.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.*;


@Data
@Builder
public class Profiles {

    @Builder.Default
    @SerializedName("profile_id_default")
    private String profileIdDefault = "kid";

    @Builder.Default
    @SerializedName("profiles_definition")
    private List<String> profilesDefinition = Arrays.asList("kid", "teenager", "adult");

}
