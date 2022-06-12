package com.example.pre_project.service;

import com.example.pre_project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);
    }

    public void sendMessageToAllAdmin(List<User> AdminUsers, User currentUser) {
        for (User user : AdminUsers) {
            send(user.getEmail(), "Пользователь хочет стать админом", "Пользователь " + currentUser.getUsername() + " хочет стать админом.");
        }
    }

}
