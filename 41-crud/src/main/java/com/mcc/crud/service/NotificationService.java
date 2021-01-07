/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nauval Abi
 */
@Service
public class NotificationService {
    
    private final JavaMailSender javaMailSender;

    //CONSTRUCTOR
    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String email;
    
    public void sendEmail() throws MessagingException{
        //SimpleMailMessage smm = new SimpleMailMessage(); //Pengirimian email ini hanya Teks
        
        MimeMessage message = javaMailSender.createMimeMessage(); //
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        //FROM
        helper.setFrom("mcc.bootcamp.41@gmail.com");
        
        //TO
        helper.setTo("kel1pastibisa@gmail.com");
        
        //SUBJECT
        helper.setSubject("Test Dari Kelompok 1");
        
        //BODY
        message.setText("<a href='Facebook.com'>Click Here</a>", "UTF-8", "html");
        
        javaMailSender.send(message);
        
    }
    
}
