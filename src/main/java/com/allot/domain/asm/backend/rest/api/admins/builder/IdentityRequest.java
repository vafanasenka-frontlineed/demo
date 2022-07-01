package com.allot.domain.asm.backend.rest.api.admins.builder;

import com.allot.domain.asm.backend.rest.api.admins.model.*;
import com.allot.domain.asm.frontend.model.User;
import lombok.experimental.UtilityClass;
import java.util.List;


@UtilityClass
public class IdentityRequest {

    public static Identity getUserIdentity(User user) {
        return Identity.builder()
                .name(user.getName())
                .identityInstances(List.of(IdentityInstance.builder()
                        .userId(user.getName())
                        .password(user.getPassword())
                        .build()))
                .name(user.getName())
                .build();
    }

}
