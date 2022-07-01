package com.allot.domain.asm.backend.rest.api.account.model;

import com.allot.domain.asm.backend.rest.api.account.model.devices.Device;
import lombok.*;
import java.util.List;


@Data
@Builder
public class Provision {

    Account account;

    @Singular
    List<Device> devices;

}
