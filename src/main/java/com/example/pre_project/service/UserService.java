package com.example.pre_project.service;

import com.example.pre_project.DTO.CastToAdminDTO;
import com.example.pre_project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<User> getAllAdminUsers();

    ResponseEntity<HttpStatus> setWantToBeAdmin();

    void add(User user);

    void delete(User user);

    ResponseEntity<HttpStatus> update(User user);

    User getById(long id);

    User getByName(String name);

    User getCurrentUser();

    ResponseEntity<HttpStatus> castToAdmin(CastToAdminDTO castToAdminDTO);
}

