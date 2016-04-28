package com.tuoppi.springsecuresession.config;

import com.tuoppi.springsecuresession.model.StatefulUserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    SecurityConfig.class,
    PersistenceContext.class,
    MethodSecurityConfig.class
})
@ComponentScan(basePackageClasses = {
    StatefulUserService.class
})
public class RootConfig {
}