/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.controllers;

import com.mcc.crud.entities.Status;
import com.mcc.crud.entities.UserDetail;
import com.mcc.crud.entities.Users;
import com.mcc.crud.service.MyUserDetailServices;
import com.mcc.crud.service.StatusService;
import com.mcc.crud.service.UserService;
import com.mcc.crud.service.VerificationService;
import com.mcc.crud.utils.HashUtility;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("api/user")
public class UserController {
    UserService us;
    VerificationService vs;
    StatusService ss;
    MyUserDetailServices ud;
//    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    
    
    @GetMapping("home")
    public String homePage(){
        return "index";
    }

    public UserController(UserService us, VerificationService vs, StatusService ss) {
        this.us = us;
        this.vs = vs;
        this.ss = ss;
    }
    
    @GetMapping("registration")
    public String regis(){
        return "registrasi";
    }
    
    @PostMapping("registration")
//    public String regisCheck(Users user) throws MessagingException{
//        us.insert(user);
//        vs.sendVerificationCode(us.getById(user.getId()));
//        return "verification";
//    }
    public String regisAdd(Users user, BindingResult result, Model model) throws MessagingException{
        if (result.hasErrors()) {
            return "registrasi";
        }
        try {
            user.setPassword(HashUtility.hash(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {            
        }
        us.insert(user);
        vs.sendVerificationCode(us.getById(user.getId()));
        return "verification";
    }
    
    @GetMapping("verification")
    public String verifCode(){
        return "verification";
    }
    
    @PostMapping("verification")
    public String verifCheck(String verifCode){
        List<Users> user = us.getAll();
        for (Users users : user) {
//            System.out.println(users.getVerificationCode());
            if (users.getVerificationCode() != null && users.getVerificationCode().equals(verifCode)) {
                users.setVerificationCode(null);
                users.setStatus(ss.getById(0));
                us.update(users);
                return "redirect:/api/user/home";
            }
        }
        return "redirect:/api/user/verification";
    }
    
    @PostMapping("login")
    public String loginCheck(Users user) {
        Users cekUser = us.getByUsername(user.getUsername());
        if (cekUser.getPassword().equals(user.getPassword()) && cekUser.getStatus().getId() != -1 && cekUser.getStatus().getId() != 3) {
            cekUser.setStatus(ss.getById(0));
            us.update(cekUser);
            System.out.println("Login Success");
            return "loginSuccess";
        } else if (cekUser.getUsername() == null || cekUser.getStatus().getId() == -1 || cekUser.getStatus().getId() == 3) {
            return "index";
        } else {
            Status newStatus = ss.getById(cekUser.getStatus().getId() + 1);
            cekUser.setStatus(newStatus);
            System.out.println(cekUser.getStatus().getId());
            us.update(cekUser);
            return "index";
        }
    }
    
//    @GetMapping("tesUsername")
//    public String findUser(String username){
//        UserDetail user = ud.findUserByUsername(username);
//        System.out.println(user.getPassword());
//        return "index";
//    }
    
}
