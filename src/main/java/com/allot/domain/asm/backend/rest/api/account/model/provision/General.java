
package com.allot.domain.asm.backend.rest.api.account.model.provision;

import com.allot.domain.asm.backend.rest.api.account.model.notifications.NotificationConfig;
import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class General {

    @SerializedName("notification_config")
    @Builder.Default
    private NotificationConfig notificationConfig = NotificationConfig.builder().build();

    @SerializedName("send_notifications")
    @Builder.Default
    private Boolean sendNotifications = false;

}
