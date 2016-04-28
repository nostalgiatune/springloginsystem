package com.tuoppi.springsecuresession.model;

import com.tuoppi.springsecuresession.service.UserManager;
import com.tuoppi.springsecuresession.user.UserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class StatefulUserServiceImpl implements StatefulUserService {
    
    private final UserManager users;
    private final UserProfile activeUser;
    
    @Autowired
    public StatefulUserServiceImpl(UserManager users) {
        this.users = users;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.activeUser = users.find(user.getUsername());
    }
    
    @Override
    public String getActiveUsername() {
        return activeUser.getUsername();
    }
    
    @Override
    public List<UserProfile> getUserProfiles() {
        return users.findAll();
    }

    @Override
    public String getActiveUserPersonalData() {
        return ((UserProfile)users.find(activeUser.getUsername())).getPersonalData();
    }
    
}