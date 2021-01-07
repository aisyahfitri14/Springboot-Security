/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;
import com.mcc.crud.entities.Employees;
import com.mcc.crud.entities.Jobs;
import com.mcc.crud.repositories.JobRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class JobService {
    JobRepository jr;
    
    @Autowired 
    public JobService(JobRepository jr) {
        this.jr = jr;
    }
    
    public List<Jobs> getAll(){
        return jr.findAll();
    }
    
    // SELECT * FROM REGIONS WHERE ID = ?
    public Jobs getById(String id){
        return jr.findById(id).get();
    }
    
    public Jobs save(Jobs job){
        return jr.save(job);
    }
    
    public void delete(String id){
        jr.deleteById(id);
    }
}
