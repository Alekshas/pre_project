package com.example.pre_project.dao;


import com.example.pre_project.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    List<User> getAllAdminUsers();

    void add(User user);

    void delete(User user);

    void update(User user);

    User getById(long id);

    User getByName(String name);

    User getCurrentUser();
}
