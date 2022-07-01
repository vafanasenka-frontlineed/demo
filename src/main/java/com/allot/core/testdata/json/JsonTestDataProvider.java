package com.allot.core.testdata.json;

import com.allot.core.testdata.*;
import com.allot.util.*;
import com.google.common.reflect.*;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class JsonTestDataProvider implements ITestDataProvider {

    @Override
    public <T extends TestDataObject> List<T> getDataList(TestDataSource dataSource) {
        return getStorage(dataSource.getTestDataObjectClass(), dataSource.getSourceFilePath())
                .entrySet()
                .stream()
                .map(stringUserEntry -> (T) stringUserEntry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public <T extends TestDataObject> T getData(TestDataSource dataSource, String name) {
        return (T) getStorage(dataSource.getTestDataObjectClass(), dataSource.getSourceFilePath()).get(name);
    }

    private <T> Map<String, T> getStorage(Class<T> clazz, String pathToFile) {
        Type map = new TypeToken<Map<String, T>>() {
            private static final long serialVersionUID = -5328428223069167554L;
        }.where(new TypeParameter<T>() {}, clazz).getType();
        Path resourcePath;
        try {
            resourcePath = Paths.get(ResourceUtil.getResourceURI(pathToFile));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Couldn't get TestData object [%s] from the %s . "
                            + "Details: %s",
                    clazz, pathToFile, e.getMessage()));
        }
        Map<String, T> storage = JsonUtils.deserializeJsonFromResources(map, clazz, resourcePath);
        return storage;
    }

}
