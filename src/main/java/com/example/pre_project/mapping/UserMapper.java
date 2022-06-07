package com.example.pre_project.mapping;

import com.example.pre_project.DTO.CreateUserDTO;
import com.example.pre_project.DTO.EditUserDTO;
import com.example.pre_project.model.Role;
import com.example.pre_project.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper {
    public User mappingEditUser(EditUserDTO editUserDTO){

        Set<Role> roles = new HashSet<>();
        if (editUserDTO.getAdmin() != null){
            roles.add(new Role(1L, "ADMIN"));
        }
        if (editUserDTO.getUser() != null){
            roles.add(new Role(2L, "USER"));
        }
        User user = new User(editUserDTO.getId(),editUserDTO.getName(), editUserDTO.getLastname(), editUserDTO.getAge(),
                editUserDTO.getEmail(), editUserDTO.getPassword(), roles);
        return user;
    }
    public User mappingCreateUser(CreateUserDTO createUserDTO){
        Set<Role> roles = new HashSet<>();

        if (createUserDTO.getAdmin() != null){
            roles.add(new Role(1L, "ADMIN"));
        }
        if (createUserDTO.getUser() != null){
            roles.add(new Role(2L, "USER"));
        }
        User user = new User(createUserDTO.getName(), createUserDTO.getLastname(), createUserDTO.getAge(),
                createUserDTO.getEmail(), createUserDTO.getPassword(), roles);
        return user;
    }

}