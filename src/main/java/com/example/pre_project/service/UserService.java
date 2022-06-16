package com.example.pre_project.service;

import com.example.pre_project.DTO.PromoteRequestDTO;
import com.example.pre_project.model.Role;
import com.example.pre_project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

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

    void updateUserRoles(User user, Set<Role> roleSet);

    ResponseEntity<HttpStatus> castToAdmin(PromoteRequestDTO castToAdminDTO);
}

