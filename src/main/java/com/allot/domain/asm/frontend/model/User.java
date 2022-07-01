package com.allot.domain.asm.frontend.model;

import com.allot.core.testdata.TestDataObject;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class User extends TestDataObject {

    @NonNull
    private String name;
    private String profile;
    private String password;
    private String email;
    private String managerId;
    private String accountId;

}
