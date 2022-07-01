package com.allot.util;

import lombok.experimental.UtilityClass;
import java.net.*;


@UtilityClass
public class ResourceUtil {

    public static URI getResourceURI(String path) throws URISyntaxException {
        ClassLoader classLoader = JsonUtils.class.getClassLoader();
        return classLoader.getResource(path).toURI();
    }

}
