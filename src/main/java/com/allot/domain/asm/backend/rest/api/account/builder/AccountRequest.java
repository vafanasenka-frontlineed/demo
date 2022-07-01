package com.allot.domain.asm.backend.rest.api.account.builder;

import com.allot.domain.asm.backend.rest.api.account.model.Account;
import com.allot.domain.asm.frontend.model.UserAccount;
import lombok.experimental.UtilityClass;


@UtilityClass
public class AccountRequest {

    public static Account addAccount(UserAccount account) {
        return Account.builder()
                .accountType(account.getType().getApiValue())
                .accountId(account.getAccountId())
                .build();
    }

}
