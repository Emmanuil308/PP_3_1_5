package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DaoUserImpl implements DaoUser {

    @PersistenceContext()
    private EntityManager em;

    public DaoUserImpl() {
    }

    public List<User> getAllUser() {

        TypedQuery<User> userTypedQuery = em.createQuery("from User", User.class);

        return userTypedQuery.getResultList();
    }

    public User getUserById(int id) {

        return em.find(User.class, id);
    }

    public void saveUser(User user) {

        if (user.getId() == 0) {
            em.persist(user);

        } else {
            em.merge(user);
        }
    }

    public void removeUserById(int id) {

        em.remove(em.find(User.class, id));
    }

    public User getUserByUserName(String userName) {

        try {
            Query query = em.createQuery("from User where userName=:paramName");
            query.setParameter("paramName", userName);
            return (User) query.getSingleResult();

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
