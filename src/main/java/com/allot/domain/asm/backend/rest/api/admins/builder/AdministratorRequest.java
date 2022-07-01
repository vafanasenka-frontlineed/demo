package com.allot.domain.asm.backend.rest.api.admins.builder;

import com.allot.domain.asm.backend.rest.api.admins.model.Administrator;
import com.allot.domain.asm.frontend.model.User;
import lombok.experimental.UtilityClass;
import java.util.List;


@UtilityClass
public class AdministratorRequest {

    public static Administrator addAdministrator(User user) {
        return Administrator.builder()
                .identity(IdentityRequest.getUserIdentity(user))
                .roles(List.of("Operations Administrator", "super_role"))
                .build();
    }

}
