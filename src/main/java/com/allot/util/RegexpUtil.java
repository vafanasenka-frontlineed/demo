package com.allot.util;

import lombok.experimental.UtilityClass;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.testng.util.Strings;

import javax.annotation.Nullable;
import java.util.regex.*;


@UtilityClass
public class RegexpUtil {

    @Nullable
    public static String findExp(@NonNull final String pattern, @NonNull final String output, int indexOfGroup) {
        if (Strings.isNotNullAndNotEmpty(pattern) && output != null) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(output);
            if (m.find()) {
                return m.group(indexOfGroup);
            }
        }
        return null;
    }

}
