
package com.allot.domain.asm.backend.rest.api.account.model.provision;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;


@Data
@Builder
public class ParentalControl {

    @SerializedName("allowed_urls")
    private List<String> allowedUrls;

    @SerializedName("blocked_categories")
    private List<String> blockedCategories;

    @SerializedName("blocked_urls")
    private List<String> blockedUrls;

    private Boolean enabled;

    @SerializedName("quiet_time")
    private QuietTime quietTime;

    @SerializedName("quiet_time_allowed_categories")
    private List<Object> quietTimeAllowedCategories;

    private List<Schedule> schedule;

    @SerializedName("send_notifications")
    @Builder.Default
    private Boolean sendNotifications = true;

}
