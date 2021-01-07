/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.entities.data;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author ASUS
 */
@Getter 
public class ResponseDetail {
    private Object data;

    public ResponseDetail(Object data) {
        this.data = data;
    }
    
    @Getter 
    public static class Status extends ResponseDetail{
        private boolean success;
        private String message;

        public Status(Object data, boolean success, String message) {
            super(data);
            this.success = success;
            this.message = message;
        }
        
        
    }
    
    
    
}
