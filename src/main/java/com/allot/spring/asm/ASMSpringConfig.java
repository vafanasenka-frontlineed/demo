package com.allot.spring.asm;

import org.springframework.context.annotation.*;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.allot")
@PropertySource(value = {"file:src/main/resources/asm/environment/${env:default}/asm.properties", "classpath:browser"
        + ".properties"})
public class ASMSpringConfig {

}
