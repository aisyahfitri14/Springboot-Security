/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.controllers;

import com.mcc.crud.entities.Departments;
import com.mcc.crud.entities.Employees;
import com.mcc.crud.entities.Jobs;
import com.mcc.crud.service.DepartmentService;
import com.mcc.crud.service.HRService;
import com.mcc.crud.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("employee-page")
public class HRController {

    private HRService hrService;
    private JobService jobService;
    private DepartmentService departService;
    
    @Autowired
        public HRController(HRService hrService, JobService jobService, DepartmentService departService) {
        this.hrService = hrService;
        this.jobService = jobService;
        this.departService = departService;
    }

    @GetMapping
    public String employeePage(Model model) {
        model.addAttribute("employee", hrService.getAll());
        return "employee-page";
    }
   
    //Halaman Employee Add
    @GetMapping("/insert")
    public String insertEmpPage(Model model){
        model.addAttribute("job", jobService.getAll());
        model.addAttribute("manager", hrService.getAll());
        model.addAttribute("department", departService.getAll());
        return "addEmp-form";
    }
//    
    //INSERT Employee
    @PostMapping("/insert")
    public String insertEmployee(Employees emp, String jobId, String managerId, String departId){ 
        Jobs job = jobService.getById(jobId);
        Employees manager = hrService.getById(Integer.parseInt(managerId));
        Departments dept = departService.getById(Integer.parseInt(departId));
        emp.setJob(job);
        emp.setDepartment(dept);
        emp.setManager(manager);
        hrService.save(emp);
        return "redirect:/employee-page";
    }

    //
    @GetMapping("update/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", hrService.getById(id));
        model.addAttribute("job", jobService.getAll());
        model.addAttribute("manager", hrService.getAll());
        model.addAttribute("department", departService.getAll());
        return "editEmp-form";
    }
    
    //UPDATE
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable String id, Employees emp, String jobId, String managerId, String departId){ 
        Employees empOld = hrService.getById(Integer.parseInt(id));
        Jobs job = jobService.getById(jobId);
        Employees manager = hrService.getById(Integer.parseInt(managerId));
        Departments dept = departService.getById(Integer.parseInt(departId));
        empOld.setFirstName(emp.getFirstName());
        empOld.setLastName(emp.getLastName());
        empOld.setEmail(emp.getEmail());
        empOld.setPhoneNumber(emp.getPhoneNumber());
        empOld.setHireDate(emp.getHireDate());
        empOld.setJob(job);
        empOld.setSalary(emp.getSalary());
        empOld.setCommissionPct(emp.getCommissionPct());
        empOld.setDepartment(dept);
        empOld.setManager(manager);
        hrService.save(empOld);
        return "redirect:/employee-page";
    }
     
    //DELETE Employee
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable Integer id){
        hrService.delete(id);
        return "redirect:/employee-page";
    }
}
