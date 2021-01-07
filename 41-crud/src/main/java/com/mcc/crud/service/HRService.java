/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.Employees;
import com.mcc.crud.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class HRService {


    private EmployeeRepository empRepository;

    @Autowired
    public HRService(EmployeeRepository empRepository) {
        this.empRepository = empRepository;
    }

    public List<Employees> getAll(){
        return empRepository.findAll();
    }

    public Employees save(Employees employee){
        return empRepository.save(employee);
    }

    public Employees getById(Integer id) {
        return empRepository.getOne(id);
    }

    public void delete (Integer id){
        empRepository.deleteById(id);
    }

}