/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.Departments;
import com.mcc.crud.entities.Jobs;
import com.mcc.crud.repositories.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service 
public class DepartmentService {
    DepartmentRepository dr;
    
    @Autowired 

    public DepartmentService(DepartmentRepository dr) {
        this.dr = dr;
    }
    
    public List<Departments> getAll(){
        return dr.findAll();
    }
    
    // SELECT * FROM REGIONS WHERE ID = ?
    public Departments getById(Integer id){
        return dr.findById(id).get();
    }
    
    public Departments save(Departments job){
        return dr.save(job);
    }
    
    public void delete(Integer id){
        dr.deleteById(id);
    }
}
