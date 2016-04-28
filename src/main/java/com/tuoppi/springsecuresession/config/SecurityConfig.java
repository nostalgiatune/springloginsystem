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
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
@Import({
    PersistenceContext.class
})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserManager userManager;
    
    @Bean
    @Transactional
    public UserDetailsService userAuthenticationManager() {
        UserService service = new UserService(userManager);
        return service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticationManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .authorizeRequests()
                .antMatchers("/init").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/admin").permitAll() // Test method level security
                .antMatchers("/**").authenticated();
    }
    
}