package com.example.pre_project.controller;

import com.example.pre_project.DTO.UserDTO;
import com.example.pre_project.mapping.UserMapper;
import com.example.pre_project.model.User;
import com.example.pre_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users/current")
    public User getCurrentUser() { return userService.getCurrentUser(); }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userService.getById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserDTO userDTO) {
        userService.add(userMapper.mappingCreateUser(userDTO));
        return ResponseEntity.ok().build();
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDTO userDTO) {
        return userService.update(userMapper.mappingEditUser(userDTO));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.delete(userService.getById(id));
        return ResponseEntity.ok().build();
    }

}