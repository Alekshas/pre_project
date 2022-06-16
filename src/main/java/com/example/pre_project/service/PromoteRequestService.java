package com.example.pre_project.service;

import com.example.pre_project.DTO.PromoteRequestDTO;
import com.example.pre_project.dao.PromoteRequestDAO;
import com.example.pre_project.dao.UserDao;
import com.example.pre_project.model.PromoteRequest;
import com.example.pre_project.model.Role;
import com.example.pre_project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PromoteRequestService {
    @Autowired
    private PromoteRequestDAO promoteRequestDAO;

    @Autowired
    private UserService userService;


    @Autowired
    private EmailSenderService emailSenderService;

    public ResponseEntity<HttpStatus> addPromoteRequest(long id) {
        promoteRequestDAO.add(new PromoteRequest(id, true));
        emailSenderService.sendMessageToAllAdmin(userService.getAllAdminUsers(), userService.getCurrentUser());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<HttpStatus> updatePromoteRequest(PromoteRequestDTO promoteRequestDTO) {
        if (promoteRequestDTO.getAdmin() != null) {
            User user = userService.getById(promoteRequestDTO.getId());
            Set<Role> roleSet = user.getRoles();
            roleSet.add(new Role(1L,"ADMIN"));
            userService.updateUserRoles(user,roleSet);
        }
        promoteRequestDAO.update(new PromoteRequest(promoteRequestDTO.getId(), promoteRequestDTO.isWantToBeAdmin()));
        return ResponseEntity.ok().build();
    }

    public List<User> getActivePromoteRequestUsers() {
        List<PromoteRequest> promoteRequestsList = promoteRequestDAO.getAllPromoteRequest();
        List<User> userList = new ArrayList<>();
        for (PromoteRequest promoteRequest : promoteRequestsList) {
            if (promoteRequest.isWantToBeAdmin()) {
                userList.add(userService.getById(promoteRequest.getUserID()));
            }
        }
        return userList;
    }

    public ResponseEntity<HttpStatus> infoAboutCurrentUserRequest() {
        if (promoteRequestDAO.getByUserID(userService.getCurrentUser().getId()).isWantToBeAdmin()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
