package com.allot.domain.asm.backend.rest.api.account.model.provision;

import lombok.*;


@Data
@Builder
@SuppressWarnings("unused")
public class AntispamIn {

    @Builder.Default
    private Boolean provisioned = false;

}
