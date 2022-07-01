package com.allot.core.driver;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface Browsers {

    Browser[] value();

}
