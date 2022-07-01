package com.allot.domain.asm.backend.rest.api.account.model.provision;

import lombok.*;


@Data
@Builder
public class AntispamOut {

    @Builder.Default
    private Boolean provisioned = false;

}
