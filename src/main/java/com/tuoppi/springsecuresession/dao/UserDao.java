package com.tuoppi.springsecuresession.dao;


import com.tuoppi.springsecuresession.user.UserProfile;
import java.util.List;

public interface UserDao {
    UserProfile find(String username);
    List<UserProfile> findAll();
    UserProfile add(UserProfile user);
    UserProfile update(UserProfile user);
    void delete(UserProfile user);
}