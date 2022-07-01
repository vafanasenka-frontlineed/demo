package com.allot.domain.asm.backend.rest.api;

import com.allot.domain.asm.backend.rest.api.auth.model.AuthenticationToken;
import org.springframework.stereotype.Component;


@Component
public class ASMSessionContext {

    private final ThreadLocal<AuthenticationToken> context = ThreadLocal.withInitial(() -> null);

    public void clear() {
        context.remove();
    }

    public AuthenticationToken getAuthenticationToken() {
        return context.get();
    }

    public void setAuthenticationToken(AuthenticationToken authenticationToken) {
        context.set(authenticationToken);
    }

}
