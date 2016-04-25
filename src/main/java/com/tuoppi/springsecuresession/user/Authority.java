package com.tuoppi.springsecuresession.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority", schema = "users")
public class Authority implements GrantedAuthority {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String userRole;

    public Authority() {
        this.userRole = null;
    }
    
    public Authority(String userRole) {
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    @Override
    public String getAuthority() {
        return getUserRole();
    }

}