package com.tuoppi.springsecuresession.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "userprofile")
public class UserProfile implements Serializable {
    
    @NotNull
    @Size(min = 1)
    private String username; // ID
    
    @NotNull
    @Size(min = 1)
    private String password;
    
    private List<Authority> authorities = new ArrayList<>();
    
    private String personalData;
    

    public UserProfile() {
    }

    public UserProfile(String username, String password, Authority... auhtorities) {
        this.username = username;
        this.password = password;
        authorities.addAll(authorities);
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
    
    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String personalData) {
        this.personalData = personalData;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nPassword: " + password + "\n"
                + "Personal data: " + personalData;
    }
    
}