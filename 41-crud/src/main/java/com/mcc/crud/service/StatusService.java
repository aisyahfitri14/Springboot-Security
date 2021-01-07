/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.Status;
import com.mcc.crud.repositories.StatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class StatusService {
    StatusRepository sr;
    
    @Autowired
    public StatusService(StatusRepository sr) {
        this.sr = sr;
    }
    
    public List<Status> getAll(){
        return sr.findAll();
    }
    
    public Status getById(Integer id){
        return sr.getOne(id);
    }
    
}
