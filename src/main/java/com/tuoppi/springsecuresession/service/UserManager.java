package com.tuoppi.springsecuresession.service;

import com.tuoppi.springsecuresession.dao.UserDao;
import com.tuoppi.springsecuresession.user.UserProfile;
import java.util.List;
import javax.annotation.PostConstruct;
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
        return users.findAll();
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
    
}