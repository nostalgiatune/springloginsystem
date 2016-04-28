package com.tuoppi.springsecuresession.config;

import com.tuoppi.springsecuresession.dao.UserDao;
import com.tuoppi.springsecuresession.model.StatefulUserService;
import com.tuoppi.springsecuresession.service.UserManager;
import com.tuoppi.springsecuresession.user.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    SecurityConfig.class
//    PersistenceContext.class,
//    MethodSecurityConfig.class
})
@ComponentScan(basePackageClasses = {
    StatefulUserService.class
//    UserDao.class,
//    UserManager.class,
//    UserService.class
})
public class RootConfig {
}