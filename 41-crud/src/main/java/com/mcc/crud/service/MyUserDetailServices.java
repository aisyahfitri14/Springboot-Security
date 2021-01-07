/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.MyUserDetail;
import com.mcc.crud.entities.Users;
import com.mcc.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class MyUserDetailServices implements UserDetailsService{
    
    @Autowired
    private UserRepository ur;
    private PasswordEncoder passwordEncorder;
    
    @Autowired
    public MyUserDetailServices(PasswordEncoder passwordEncorder) {
        this.passwordEncorder = passwordEncorder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = ur.getByUsername(username);
        user.setPassword(passwordEncorder.encode(user.getPassword()));
        
        if (user == null) {
            throw new UsernameNotFoundException("Username incorrect");
        }
        
        return new MyUserDetail(user);
    }
    
}
