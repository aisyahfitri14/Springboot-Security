/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ASUS
 */
@Data
public class MyUserDetail implements UserDetails{
    
    private Users user;
    
    @Autowired
    public MyUserDetail(Users user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserRoles> roles = user.getUserRolesList();
        List<UserPermissions> permissions = user.getUserPermissionsList();
        
        for (UserRoles role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleId().getName().toUpperCase()));
        }
        
        for (UserPermissions permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getPermissionId().getName().toUpperCase()));
        }
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsUserVerified();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsUserVerified();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsUserVerified();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsUserVerified();
    }
    
}
