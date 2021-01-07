/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.service;

import com.mcc.crud.entities.Countries;
import com.mcc.crud.repositories.CountryRepository;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nauval Abi
 */
@Service
public class CountriesService {

    CountryRepository countryRepository;
    NotificationService notifService; //Tambahan JMS

    //CONSTRUCTOR 
    @Autowired
    public CountriesService(CountryRepository countryRepository, NotificationService notifService) {
        this.countryRepository = countryRepository;
        this.notifService = notifService; //Tambahan JMS
    }

    //Menampilkan Country dan Regionnya
//    public List<CountryRegion> getAllCountriesAndRegions() {
//        List<Countries> countries = countryRepository.findAll();
//        List<CountryRegion> crs = new ArrayList<>();
//        for (Countries country : countries) {
//            CountryRegion countryRegion = new CountryRegion(
//                    country.getName(), //countryName
//                    country.getRegion().getName() //regionName
//            );
//            crs.add(countryRegion);
//        }
//        return crs;
//    }

//    public List<Countries> getAllCountries() {
//        List<Countries> countries = countryRepository.findAll();
//        return countries;
//    }
    
    //READ or LIST
    public List<Countries> getAllCountryRegionName() {
        List<Countries> country = countryRepository.findAll();
        return country;
    }

    //GET ID
    public Countries getCountryById(String id) throws MessagingException { //Tambahan throws JMS
        notifService.sendEmail();
        return countryRepository.findById(id).get();
    }

    //SAVE
    public Countries save(Countries country) {
        return countryRepository.save(country);
    }

    //DELETE
    public boolean delete(String id) {
        try {
            countryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
