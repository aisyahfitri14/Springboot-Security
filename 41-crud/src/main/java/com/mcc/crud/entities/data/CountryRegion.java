/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.entities.data;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ASUS
 */

@Data 
public class CountryRegion {
    String countryName, regionName;
    
    @Autowired 
    public CountryRegion(String countryName, String regionName) {
        this.countryName = countryName;
        this.regionName = regionName;
    }
    
    
}
