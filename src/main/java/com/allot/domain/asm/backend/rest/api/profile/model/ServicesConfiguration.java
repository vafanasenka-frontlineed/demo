
package com.allot.domain.asm.backend.rest.api.profile.model;

import com.allot.domain.asm.backend.rest.api.account.model.provision.*;
import com.allot.domain.asm.backend.rest.api.account.model.provision.location.LocationManagement;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class ServicesConfiguration {

    @SerializedName("ads_free")
    private AdsFree adsFree;

    @SerializedName("anti_bullying")
    private AntiBullying antiBullying;

    @SerializedName("auto_notice")
    private AutoNotice autoNotice;

    @SerializedName("general")
    private General general;

    @SerializedName("location_management")
    private LocationManagement locationManagement;

    @SerializedName("parental_control")
    private ParentalControl parentalControl;

    @SerializedName("threat_protection")
    private ThreatProtection threatProtection;

}
