/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.entities.data;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class Status {
    private boolean success;
    private String message;

    public Status() {
    }

    public Status(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    
    
}
