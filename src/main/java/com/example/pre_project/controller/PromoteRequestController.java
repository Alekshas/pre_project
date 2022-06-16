package com.example.pre_project.controller;

import com.example.pre_project.DTO.PromoteRequestDTO;
import com.example.pre_project.model.User;
import com.example.pre_project.service.PromoteRequestService;
import com.example.pre_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromoteRequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PromoteRequestService promoteRequestService;

    @GetMapping("/promoteRequest")
    public ResponseEntity<HttpStatus> requestToCastUserToAdmin() {
        return promoteRequestService.addPromoteRequest(userService.getCurrentUser().getId());
    }

    @GetMapping("/promoteRequest/info")
    public ResponseEntity<HttpStatus> requestInfoUser() {
        return promoteRequestService.infoAboutCurrentUserRequest();
    }

    @PostMapping("/promoteRequest")
    public ResponseEntity<HttpStatus> updatePromoteRequest(@RequestBody PromoteRequestDTO promoteRequestDTO) {

        return promoteRequestService.updatePromoteRequest(promoteRequestDTO);
    }
    @GetMapping("/promoteRequest/users")
    public List<User> getAllUsers() {
        return promoteRequestService.getActivePromoteRequestUsers();
    }
}
