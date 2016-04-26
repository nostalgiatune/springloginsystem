package com.tuoppi.springsecuresession.config;


import com.tuoppi.springsecuresession.dao.UserDao;
import com.tuoppi.springsecuresession.service.UserManager;
import com.tuoppi.springsecuresession.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@Import({
    PersistenceContext.class,
})
@ComponentScan(basePackageClasses = {
    UserService.class,
    UserManager.class,
    UserDao.class
})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userAuthenticationService;
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .authorizeRequests()
                .antMatchers("/init").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/**").authenticated()
                .anyRequest().denyAll();
    }
    
}