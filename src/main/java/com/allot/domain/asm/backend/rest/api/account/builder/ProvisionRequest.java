package com.allot.domain.asm.backend.rest.api.account.builder;

import com.allot.domain.asm.backend.rest.api.account.model.Provision;
import com.allot.domain.asm.backend.rest.api.account.model.devices.Device;
import com.allot.domain.asm.frontend.model.*;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ProvisionRequest {

    public static Provision provision(UserAccount userAccount, UserDevice userDevice) {
        var account = AccountRequest.addAccount(userAccount);
        var device = Device.builder()
                .accountId(userAccount.getAccountId())
                .description(userDevice.getDescription())
                .platform(userDevice.getDevicePlatform().name().toLowerCase())
                .build();
        return Provision.builder()
                .account(account)
                .device(device)
                .build();
    }

}
