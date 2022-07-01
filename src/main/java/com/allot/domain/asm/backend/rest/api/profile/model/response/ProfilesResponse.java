package com.allot.domain.asm.backend.rest.api.profile.model.response;

import com.allot.domain.asm.backend.rest.api.profile.model.Profile;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Data
@Builder
public class ProfilesResponse {

    @SerializedName("items")
    private List<Profile> profileItems;

}
