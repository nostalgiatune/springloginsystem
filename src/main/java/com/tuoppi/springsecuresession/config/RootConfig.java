package com.tuoppi.springsecuresession.config;

import com.tuoppi.springsecuresession.dao.UserDao;
import com.tuoppi.springsecuresession.model.StatefulUserServiceImpl;
import com.tuoppi.springsecuresession.service.UserManager;
import com.tuoppi.springsecuresession.user.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    PersistenceContext.class,
    SecurityConfig.class,
    MethodSecurityConfig.class
})
@ComponentScan(basePackageClasses = {
    UserDao.class,
    UserManager.class,
    StatefulUserServiceImpl.class,
    UserService.class
})
public class RootConfig {
}