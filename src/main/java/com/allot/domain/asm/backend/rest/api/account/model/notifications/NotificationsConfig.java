
package com.allot.domain.asm.backend.rest.api.account.model.notifications;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class NotificationsConfig {

    @SerializedName("ad_blocked")
    private Boolean adBlocked;

    @SerializedName("antibotnet")
    private Boolean antibotnet;

    @SerializedName("antimalware_category")
    private Boolean antimalwareCategory;

    @SerializedName("antiphishing")
    private Boolean antiphishing;

    @SerializedName("antivirus")
    private Boolean antivirus;

    @SerializedName("blocked_url")
    private Boolean blockedUrl;

    @SerializedName("geo_fence_violation")
    private Boolean geoFenceViolation;

    @SerializedName("harmful_conversation")
    private Boolean harmfulConversation;

    @SerializedName("quiet_time_ended")
    private Boolean quietTimeEnded;

    @SerializedName("quiet_time_released")
    private Boolean quietTimeReleased;

    @SerializedName("quiet_time_started")
    private Boolean quietTimeStarted;

}
