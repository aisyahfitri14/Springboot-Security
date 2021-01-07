/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.repositories;

import com.mcc.crud.entities.Employees;
import com.mcc.crud.entities.Locations;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository 
public interface EmployeeRepository extends JpaRepository<Employees, Integer>{

}
