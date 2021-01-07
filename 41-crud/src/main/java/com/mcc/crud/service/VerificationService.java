/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.Users;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class VerificationService {
    private final JavaMailSender javaMailSender;
    private final UserService userSevice;
    
    @Value("${spring.mail.username}")
    private String email;

    public VerificationService(JavaMailSender javaMailSender, UserService userSevice) {
        this.javaMailSender = javaMailSender;
        this.userSevice = userSevice;
    }
    
    public void sendVerificationCode(Users user) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage(); //
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        UUID uuid = UUID.randomUUID();
        
        //FROM
        helper.setFrom(email);
        
        //TO
        helper.setTo(user.getEmployees().getEmail());
        
        //SUBJECT
        helper.setSubject("Verification Code");
        
        //BODY
        message.setText("<h1> Verification Code </h1>" 
                + "<p> Your verification code : " + uuid.toString() + "</p>" 
                + "<p> Verification link : <a href='http://localhost:8070/api/user/verification'>Click Here</a>" 
                + "<p> KEEP IT SECRET !!!", "UTF-8", "html");
        
        user.setVerificationCode(uuid.toString());
        userSevice.update(user);
        javaMailSender.send(message);
    }
}
