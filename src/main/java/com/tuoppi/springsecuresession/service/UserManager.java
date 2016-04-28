package com.tuoppi.springsecuresession.service;

import com.tuoppi.springsecuresession.dao.UserDao;
import com.tuoppi.springsecuresession.user.UserProfile;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, transactionManager = "transactionManager")
public class UserManager {
    
    @Autowired
    private UserDao users;
    
    @PostConstruct
    public void debugMessage() {
        System.out.println("usermanager ok");
    }
    
    public boolean exist(UserProfile user) {
        return users.find(user.getUsername()) != null;
    }
    
    public UserProfile find(String username) {
        return users.find(username);
    }
    
    public List<UserProfile> findAll() {
        List<UserProfile> profiles = users.findAll();
        
        // Fetch proxied auhtorities before closing session
        profiles.forEach((UserProfile profile) -> profile.getAuthorities().size());
        
        return profiles;
    }
    
    public UserProfile add(UserProfile user) throws RuntimeException {
        
        if (users.find(user.getUsername()) != null)
            throw new RuntimeException("User exist");
        
        return users.add(user);
    }
    
    public UserProfile update(UserProfile user) {
        return users.update(user);
    }
    
    public void delete(UserProfile user) {
        users.delete(user);
    }
    
    @RolesAllowed({"ROLE_ADMIN"})
    public String adminApi() {
        return "ADMIN authorized";
    }
    
}