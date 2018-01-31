
package br.edu.ifpb.domain.service;

import br.edu.ifpb.domain.model.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 31/01/2018
 */

@Stateless
public class Users {
    
    @PersistenceContext
    private EntityManager manager;
    
    @PostConstruct
    public void init() {
        this.add(User.of("admin@admin.com", "admin"));
    }
    
    public List<User> all() {
        return manager.createQuery("FROM User u", User.class)
                      .getResultList();
    }
    
    public User with(String email) {
        return manager.find(User.class, email);
    }
    
    public User add(User user) {
        manager.persist(user);
        return user;
    }
}
