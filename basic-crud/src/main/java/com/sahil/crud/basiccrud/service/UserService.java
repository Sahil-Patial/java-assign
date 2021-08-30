package com.sahil.crud.basiccrud.service;

import java.util.*;

import com.sahil.crud.basiccrud.model.User;

public interface UserService {
    
    public List<User> findAllUsers();

    public String screenUserById(int theId);

    public User findUserById(int theId);

    public User saveUser(User theUser);

    public int deleteUserById(int theId);
    
}
