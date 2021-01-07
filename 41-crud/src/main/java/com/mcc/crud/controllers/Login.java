/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.controllers;

import com.mcc.crud.service.StatusService;
import com.mcc.crud.service.UserService;
import com.mcc.crud.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ASUS
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Login {
    UserService us;
    VerificationService vs;
    StatusService ss;
    
    @Autowired 
    public Login(UserService us, VerificationService vs, StatusService ss) {
        this.us = us;
        this.vs = vs;
        this.ss = ss;
    }
    
    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN')")
    public String readAdmin(){
        return "home";
    }
    
    @GetMapping("/write")
    @PreAuthorize("hasAnyAuthority('WRITE_ADMIN')")
    public String writeAdmin(){
        return "write";
    }
    
    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN')")
    public String updateAdmin(){
        return "update";
    }
    
    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN')")
    public String deleteAdmin(){
        return "delete";
    }
    
    
    
    
}
