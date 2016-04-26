package com.tuoppi.springsecuresession.dao;

import com.tuoppi.springsecuresession.user.UserProfile;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @PostConstruct
    public void debugMessage() {
        System.out.println("userdao ok");
    }

    @Override
    public UserProfile find(String username) {
        return em.find(UserProfile.class, username);
    }
    
    @Override
    public List<UserProfile> findAll() {
        CriteriaQuery<UserProfile> criteria = em.getCriteriaBuilder().createQuery(UserProfile.class);
        criteria.select(criteria.from(UserProfile.class));
        List<UserProfile> profiles = em.createQuery(criteria).getResultList();
        return profiles;
    }

    @Override
    public UserProfile add(UserProfile user) {
        em.persist(user);
        return em.merge(user);
    }

    @Override
    public UserProfile update(UserProfile user) {
        return em.merge(user);
    }

    @Override
    public void delete(UserProfile user) {
        em.remove(em.merge(user));
    }
    
}