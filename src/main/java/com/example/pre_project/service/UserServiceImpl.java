package com.example.pre_project.service;

import com.example.pre_project.DTO.CastToAdminDTO;
import com.example.pre_project.dao.UserDao;
import com.example.pre_project.model.Role;
import com.example.pre_project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getAllAdminUsers() {
        return userDao.getAllAdminUsers();
    }

    @Override
    public ResponseEntity<HttpStatus> setWantToBeAdmin() {

        User currentUser = getById(getCurrentUser().getId());

        currentUser.setWantToBeAdmin(true);


        emailSenderService.sendMessageToAllAdmin(userDao.getAllAdminUsers(), getCurrentUser());

        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok().build();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public ResponseEntity<HttpStatus> update(User user) {
        if (Objects.equals(user.getPassword(), "") || Objects.isNull(user.getPassword())) {
            user.setPassword(getById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
        user.setWantToBeAdmin(getById(user.getId()).isWantToBeAdmin());

        userDao.update(user);

        if (user.getId() == getCurrentUser().getId() && !user.getRoles().equals(getCurrentUser().getRoles())) {
            SecurityContextHolder.getContext().setAuthentication(null);

            URI logout = null;
            try {
                logout = new URI("http://localhost:8080/logout");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(logout);
            return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User getCurrentUser() {
        return userDao.getCurrentUser();
    }

    @Override
    public ResponseEntity<HttpStatus> castToAdmin(CastToAdminDTO castToAdminDTO) {
        User user = getById(castToAdminDTO.getId());
        if(!Objects.isNull(castToAdminDTO.getAdmin()) && castToAdminDTO.getAdmin().equals("ADMIN")){
            user.getRoles().add(new Role(1L,"ADMIN"));
        }
        user.setWantToBeAdmin(castToAdminDTO.isWantToBeAdmin());
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getByName(s);
        return user;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
