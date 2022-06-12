package com.example.pre_project.dao;


import com.example.pre_project.model.Role;
import com.example.pre_project.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<User> getAllAdminUsers() {
        ArrayList<User> allUsers = (ArrayList<User>) getAllUsers();
        ArrayList<User> adminUsers = new ArrayList<>();
        for (User user : allUsers) {
            if(user.getRoles().contains(new Role(1L,"ADMIN"))){
                adminUsers.add(user);
            }
        }
        return adminUsers;
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        return entityManager.createQuery("select u from User u where u.email= :name", User.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public User getCurrentUser() { return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();}

    public UserDaoImpl() {
        super();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}