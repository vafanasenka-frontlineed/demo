package com.allot.core.context;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class SessionContext {

    private final ThreadLocal<Map<String, String>> cookiesStorage = ThreadLocal.withInitial(HashMap::new);
    private final ThreadLocal<Long> validateNumStorage = ThreadLocal.withInitial(() -> null);

    public void clear() {
        cookiesStorage.remove();
        validateNumStorage.remove();
    }

    public Map<String, String> getCookies() {
        return cookiesStorage.get();
    }

    public Long getValidateRandNum() {
        return validateNumStorage.get();
    }

    public void setCookies(@NonNull final String name, @NonNull final String value) {
        log.debug("Set cookie to the storage : {}={}", name, value);
        cookiesStorage.get().put(name, value);
    }

    public void setCookies(@NonNull final CSCookie cookie, @NonNull final Response response) {
        String cookieName = cookie.getName();
        setCookies(cookieName, response.getCookie(cookieName));
    }

    public void setValidateRandNum(long validateRandNum) {
        log.debug("Set validateRandNum to the storage : {}", validateRandNum);
        validateNumStorage.set(validateRandNum);
    }

    @AllArgsConstructor
    public enum CSCookie {
        BSET("bSet"),
        JSESSIONID("JSESSIONID"),
        MSTR_AUTH("MSTR_AUTH"),
        ;

        @Getter
        private String name;
    }

}
