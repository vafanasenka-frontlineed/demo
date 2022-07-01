package com.allot.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.*;


@UtilityClass
public class JsonUtils {

    public static <T> T deserializeJsonFromResources(Type type, Class<?> clazz, Path resourcePath) {
        try (JsonReader reader = new JsonReader(Files.newBufferedReader(resourcePath))) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't read file %s . Details: %s.",
                    resourcePath.getFileName(), e
                            .getMessage()));
        }
    }

}
