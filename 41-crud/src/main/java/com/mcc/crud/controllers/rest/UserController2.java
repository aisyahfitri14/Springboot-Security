/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.controllers.rest;

import com.mcc.crud.entities.Users;
import com.mcc.crud.entities.data.ResponseDetail;
import com.mcc.crud.service.StatusService;
import com.mcc.crud.service.UserService;
import com.mcc.crud.service.VerificationService;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.service.ResponseMessage;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("api/user2")
public class UserController2 {
    UserService us;
    VerificationService vs;
    StatusService ss;
    
    @Autowired 
    public UserController2(UserService us, VerificationService vs, StatusService ss) {
        this.us = us;
        this.vs = vs;
        this.ss = ss;
    }
    
    @GetMapping("")
    public String home(){
        return "index";
    }
    
    @GetMapping("admin")
    public String adminHome(){
        return "indexAdmin";
    }
    
    @GetMapping("all")
    @ResponseBody
    public ResponseDetail getAll(){
        return new ResponseDetail(us.getAll());
    }
    
    @PostMapping("")
    @ResponseBody
    public ResponseDetail register(@RequestBody Users user) throws MessagingException{
        us.insert(user);
        vs.sendVerificationCode(us.getById(user.getId()));
        return new ResponseDetail.Status(user, true, "Registration Success ! Please check your email for verification link");
    }
    
    @DeleteMapping("")
    @ResponseBody
    public ResponseDetail delete(@RequestBody Users user){
        try {
            return new ResponseDetail.Status(null, true, "Delete User Success");
        } catch (Exception e) {
            return new ResponseDetail.Status(null, false, "Delete User Failed");
        }
    }
    
//    @PostMapping("verification")
//    public ResponseDetail verificationPage(String verifCode){
////        model.addAttribute("user", us.getById(id));
////        us.getVerificationCode(verifCode);
//Users user = new Users
//        if (user.getVerificationCode().equals(verifCode)) {
//            user.setVerificationCode(null);
//            user.setStatus(ss.getById(0));
//            us.update(user);
//            return new ResponseDetail.Status(null, true, "Verification Success !");
//        }
//        else {
//            return new ResponseDetail.Status(null, false, "Verification Failed !");
//        }
//    }
    
    @PostMapping("verifications")
    public ResponseDetail checkVerification(@PathVariable Integer id, String verificationCode){
        Users user = us.getById(id);
        if (user.getVerificationCode().equals(verificationCode)) {
            user.setVerificationCode(null);
            user.setStatus(ss.getById(0));
            us.update(user);
            return new ResponseDetail.Status(null, true, "Verification Success !");
        }
        else {
            return new ResponseDetail.Status(null, false, "Verification Failed !");
        }
    }
    
    
}
