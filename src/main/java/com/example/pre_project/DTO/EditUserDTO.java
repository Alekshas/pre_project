package com.example.pre_project.DTO;

import lombok.Data;

@Data
public class EditUserDTO {

    private long id;

    private String name;

    private String lastname;

    private int age;

    private String email;

    private String password;

    private String admin;

    private String user;

}
