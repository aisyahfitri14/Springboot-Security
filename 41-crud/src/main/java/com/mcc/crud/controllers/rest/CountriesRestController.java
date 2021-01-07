/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.controllers.rest;

import com.mcc.crud.entities.Countries;
import com.mcc.crud.entities.MyUserDetail;
import com.mcc.crud.entities.data.ResponseDetail;
import com.mcc.crud.entities.data.Status;
import com.mcc.crud.service.CountriesService;
import com.mcc.crud.service.MyUserDetailServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nauval Abi
 */
@RestController
@RequestMapping("api/country")
public class CountriesRestController {
    
    CountriesService countryService;
    MyUserDetailServices ud;
    
    //CONSTRUCTOR
    @Autowired
    public CountriesRestController(CountriesService countryService) {
        this.countryService = countryService;
    }
    
    //READ
    @GetMapping("")
    public Map<String, List<Countries>> getAll(){
        Map<String, List<Countries>> result = new HashMap<>();
        result.put("data", countryService.getAllCountryRegionName());
        return result;
    }
    
    //Tambahan throws (JMS)
    @GetMapping("{id}")
    public ResponseDetail getById(@PathVariable String id) throws MessagingException{
        return new ResponseDetail(countryService.getCountryById(id));
    }
    
//    @GetMapping("{username}")
//    public ResponseDetail findUser(@PathVariable String username) throws MessagingException{
//        return new ResponseDetail(ud.findUserByUsername(username));
//    }
    
    //CREATE
    @PostMapping("insert")
    public ResponseDetail insert(@RequestBody Countries country){
        return new ResponseDetail.Status(countryService.save(country), true, "Berhasil Insert Country");
    }
    
//    @PostMapping("test")
//    public ResponseDetail findUser(@RequestBody String username){
//        return new ResponseDetail(ud.findUserByUsername(username));
//    }
    
    
    //UPDATE
    @PutMapping("update")
    public ResponseDetail update(@RequestBody Countries country){
        return new ResponseDetail.Status(countryService.save(country), true, "Berhasil Update Country");
    }
    
    //DELETE
    @DeleteMapping("delete/{id}")
    public ResponseDetail delete(String id){
        return new ResponseDetail.Status(null, countryService.delete(id), "Berhasil Delete Country");
    }
}
