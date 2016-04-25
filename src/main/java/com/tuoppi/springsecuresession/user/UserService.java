package com.tuoppi.springsecuresession.user;


import com.tuoppi.springsecuresession.service.UserManager;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserManager users;
    
    @PostConstruct
    public void debugMessage() {
        System.out.println("userservice ok");
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        
        UserProfile profile = users.find(name);
        if (profile == null)
            throw new UsernameNotFoundException(name);
        
        return new User(profile.getUsername(), profile.getPassword(),
                profile.getAuthorities());
    }
    
}