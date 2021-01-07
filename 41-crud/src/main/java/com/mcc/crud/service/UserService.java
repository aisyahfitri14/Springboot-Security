/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.Status;
import com.mcc.crud.entities.Users;
import com.mcc.crud.repositories.StatusRepository;
import com.mcc.crud.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author ASUS
 */
@Service
public class UserService {
    UserRepository ur;
    StatusRepository sr;
    
    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }
    
    public List<Users> getAll(){
        return ur.findAll();
    }
    
    public Users getById(Integer id){
        return ur.getOne(id);
    }
    
    public Users getByUsername(String username){
        return ur.getByUsername(username);
    }
    
    public Users getVerifCode(String verifCode){
        return ur.getVerifCode(verifCode);
    }
    
    public Users insert(Users user){
        ur.insertUser(user.getId(), user.getUsername(), user.getPassword(), -1);
        return user;
    }
    
    public Users update(Users user){
        ur.updateUser(user.getUsername(), user.getPassword(), user.getVerificationCode(), user.getStatus().getId(), user.getId());
        return user;
    }
    
    public Users delete(Integer id){
        ur.deleteById(id);
        return ur.getOne(id);
    }
    
    public void updateStatus(Users user){
        if (user.getStatus().getId() != 3) {
            Integer newStatus = user.getStatus().getId() + 1;
            ur.updateStatusUser(newStatus, user.getId());
        }
        
        if (user.getStatus().getId() == 3) {
            System.out.println("LOCKED");
            updateVerified(user);
        }
    }
    
    public void resetStatus(Users user){
        ur.updateStatusUser(0, user.getId());
        ur.updateVerifiedUser(true, user.getId());
    }
    
    public void updateVerified(Users user){
        ur.updateVerifiedUser(false, user.getId());
    }
    
}
