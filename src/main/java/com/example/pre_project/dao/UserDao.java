package com.example.pre_project.dao;


import com.example.pre_project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void add(User user);

    void delete(User user);

    ResponseEntity<HttpStatus> update(User user);

    User getById(long id);

    User getByName(String name);

    User getCurrentUser();
}
