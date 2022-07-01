package com.allot.core.driver;

import java.lang.annotation.*;


/**
 * Annotate a class or method with {@link Browser} and add as annotation value a list of test tags
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Repeatable(Browsers.class)
public @interface Browser {

    String value();

}
