package com.example.pre_project.mapping;

import com.example.pre_project.DTO.UserDTO;
import com.example.pre_project.model.Role;
import com.example.pre_project.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {
    public User mappingEditUser(UserDTO userDTO){

        Set<Role> roles = new HashSet<>();
        if (userDTO.getAdmin() != null){
            roles.add(new Role(1L, "ADMIN"));
        }
        if (userDTO.getUser() != null){
            roles.add(new Role(2L, "USER"));
        }
        User user = new User(userDTO.getId(),userDTO.getName(), userDTO.getLastname(), userDTO.getAge(),
                userDTO.getEmail(), userDTO.getPassword(), roles);
        return user;
    }
    public User mappingCreateUser(UserDTO userDTO){
        Set<Role> roles = new HashSet<>();

        if (userDTO.getAdmin() != null){
            roles.add(new Role(1L, "ADMIN"));
        }
        if (userDTO.getUser() != null){
            roles.add(new Role(2L, "USER"));
        }
        User user = new User(userDTO.getName(), userDTO.getLastname(), userDTO.getAge(),
                userDTO.getEmail(), userDTO.getPassword(), roles);
        return user;
    }

}