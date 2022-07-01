package com.allot.spring.cs;

import org.springframework.context.annotation.*;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.allot")
@PropertySource(value = {"file:src/main/resources/cs/environment/cs.properties", "classpath:browser.properties"})
public class ClearSeeSpringConfig {

}
