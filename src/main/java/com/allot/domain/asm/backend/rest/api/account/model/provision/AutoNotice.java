package com.allot.domain.asm.backend.rest.api.account.model.provision;

import lombok.*;


@Data
@Builder
public class AutoNotice {

    @Builder.Default
    private Boolean provisioned = false;

}
