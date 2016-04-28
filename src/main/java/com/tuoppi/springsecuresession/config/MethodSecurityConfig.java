package com.tuoppi.springsecuresession.config;

import com.tuoppi.springsecuresession.dao.UserDao;
import com.tuoppi.springsecuresession.service.UserManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true,
        prePostEnabled = true)
@ComponentScan(basePackageClasses = {
    UserManager.class,
    UserDao.class
})
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}