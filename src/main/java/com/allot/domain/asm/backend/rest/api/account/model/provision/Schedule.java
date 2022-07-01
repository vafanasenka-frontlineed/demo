
package com.allot.domain.asm.backend.rest.api.account.model.provision;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;


@Data
@Builder
public class Schedule {

    private String action;

    @SerializedName("days_of_week")
    private List<String> daysOfWeek;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("start_time")
    private String startTime;

}
