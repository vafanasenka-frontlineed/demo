package com.allot.domain.asm.frontend.model;

import com.allot.core.testdata.TestDataObject;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class UserDevice extends TestDataObject {

    @NonNull
    private String description;
    private String deviceId;
    private String accountId;
    private DevicePlatform devicePlatform;
    private DeviceType deviceType;

}
