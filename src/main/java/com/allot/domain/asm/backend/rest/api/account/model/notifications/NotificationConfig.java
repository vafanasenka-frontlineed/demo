
package com.allot.domain.asm.backend.rest.api.account.model.notifications;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class NotificationConfig {

    @SerializedName("battery")
    private Battery battery;

    @SerializedName("device_app_lost_privileges")
    @Builder.Default
    private Boolean deviceAppLostPrivileges = false;

    @SerializedName("device_data_changed")
    @Builder.Default
    private Boolean deviceDataChanged = false;

    @SerializedName("device_deletion_forced")
    @Builder.Default
    private Boolean deviceDeletionForced = false;

    @SerializedName("internet_availability")
    @Builder.Default
    private InternetAvailability internetAvailability = InternetAvailability.builder().build();

    @SerializedName("new_device_activated")
    @Builder.Default
    private Boolean newDeviceActivated = false;

}
