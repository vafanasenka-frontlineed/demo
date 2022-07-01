package com.allot.util;

import lombok.*;
import lombok.experimental.UtilityClass;
import okhttp3.HttpUrl;
import org.checkerframework.checker.nullness.qual.NonNull;


@UtilityClass
public class HttpUrlBuilder {

    public static String build(@NonNull final HTTPSchema schema, @NonNull final String host, int port,
            @NonNull final String path) {
        return new HttpUrl.Builder()
                .scheme(schema.getValue())
                .host(host)
                .port(port)
                .addPathSegments(path)
                .build()
                .toString();
    }

    @Getter
    @AllArgsConstructor
    public enum HTTPSchema {

        HTTP("http"),
        HTTPS("https"),
        ;

        String value;

    }

}
