
package com.allot.domain.asm.backend.rest.api.account.model.provision.location;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class GeoFenceArea {

    @SerializedName("area_id")
    private String areaId;

    @SerializedName("creation_time")
    private String creationTime;

    @SerializedName("is_restricted")
    private Boolean isRestricted;

    @SerializedName("latitude")
    private Long latitude;

    @SerializedName("longitude")
    private Long longitude;

    @SerializedName("radius_meters")
    private Long radiusMeters;

}
