/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.configuration;

import com.mcc.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */

@Component
public class SuccessEvent implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private UserService us;
    
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        System.out.println("SUKSES LOGIN");
//        System.out.println(us.getByUsername(e.getAuthentication().getName()));
        us.resetStatus(us.getByUsername(e.getAuthentication().getName()));
    }
    
}
