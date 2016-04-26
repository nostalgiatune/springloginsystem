package com.tuoppi.springsecuresession.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true,
        prePostEnabled = true)
@Import(SecurityConfig.class)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}