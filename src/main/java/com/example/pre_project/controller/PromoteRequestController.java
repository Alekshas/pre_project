package com.example.pre_project.controller;

import com.example.pre_project.DTO.PromoteRequest;
import com.example.pre_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PromoteRequestController {

    @Autowired
    private UserService userService;

    @GetMapping("/promoteRquest")
    public ResponseEntity<HttpStatus> requestToCastUserToAdmin() {
        return userService.setWantToBeAdmin();
    }

    @PostMapping("/promoteRquest")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody PromoteRequest castToAdminDTO) {
        return userService.castToAdmin(castToAdminDTO);
    }

}
