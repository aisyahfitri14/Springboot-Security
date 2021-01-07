/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.crud.repositories;

import com.mcc.crud.entities.MyUserDetail;
import com.mcc.crud.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
    
    @Query(value="SELECT verification_code, status FROM users WHERE verification_code = ?1", nativeQuery = true)
    Users getVerifCode(String verifCode);
    
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery=true)
    Users getByUsername(String username);
    
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery=true)
    MyUserDetail getUserByUsername(String username);
    
    @Modifying
    @Transactional
    @Query(value="INSERT INTO users(id, username, password, status) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertUser(Integer id, String username, String password, Integer status);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE users SET username = ?1, password = ?2, verification_code = ?3, status = ?4 WHERE id = ?4", nativeQuery = true)
    void updateUser(String username, String password, String verCode, Integer status, Integer id);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE users SET status = ?1 WHERE id = ?2", nativeQuery = true)
    void updateStatusUser(Integer status, Integer id);
    
    @Modifying
    @Transactional
    @Query(value="UPDATE users SET is_user_verified = ?1 WHERE id = ?2", nativeQuery = true)
    void updateVerifiedUser(boolean isUserVerified, Integer id);
}
