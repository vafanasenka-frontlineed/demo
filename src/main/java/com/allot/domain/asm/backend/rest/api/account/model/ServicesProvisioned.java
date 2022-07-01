package com.allot.domain.asm.backend.rest.api.account.model;

import com.allot.domain.asm.backend.rest.api.account.model.provision.*;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class ServicesProvisioned {

    @SerializedName("ads_free")
    private AdsFree adsFree;

    @SerializedName("antispam_in")
    private AntispamIn antispamIn;

    @SerializedName("antispam_out")
    private AntispamOut antispamOut;

    @SerializedName("auto_notice")
    private AutoNotice autoNotice;

    private Firewall firewall;

    @SerializedName("parental_control")
    private ParentalControl parentalControl;

    @SerializedName("threat_protection")
    private ThreatProtection threatProtection;

    private Webproxy webproxy;

}
