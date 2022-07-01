
package com.allot.domain.asm.backend.rest.api.account.model.provision.location;

import com.allot.domain.asm.backend.rest.api.account.model.notifications.NotificationsConfig;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Data
@Builder
public class LocationManagement {

    @SerializedName("enabled")
    private Boolean enabled;

    @SerializedName("geo_fence_areas")
    private List<GeoFenceArea> geoFenceAreas;

    @SerializedName("notifications_config")
    private NotificationsConfig notificationsConfig;

    @SerializedName("send_notifications")
    private Boolean sendNotifications;

}
