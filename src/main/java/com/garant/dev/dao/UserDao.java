package com.garant.dev.dao;

import java.util.List;

import com.garant.dev.model.User;

public interface UserDao {
 
    User findById(int id);
     
    User findBySSO(String sso);
     
    void save(User user);
     
    void deleteBySSO(String sso);
     
    List<User> findAllUsers();
 
}