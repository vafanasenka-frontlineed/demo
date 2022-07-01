package com.allot.domain.asm.backend.rest.api.admins.builder;

import com.allot.domain.asm.backend.rest.api.admins.model.Manager;
import com.allot.domain.asm.frontend.model.User;
import lombok.experimental.UtilityClass;

import static com.allot.domain.asm.backend.rest.api.admins.builder.IdentityRequest.getUserIdentity;


@UtilityClass
public class ManagerRequest {

    public static Manager addManagerToAccount(User user, String accountId) {
        return Manager.builder()
                .accountId(accountId)
                .identity(getUserIdentity(user))
                .build();
    }

    public static Manager addManager(User user) {
        return Manager.builder()
                .identity(getUserIdentity(user))
                .build();
    }

}
