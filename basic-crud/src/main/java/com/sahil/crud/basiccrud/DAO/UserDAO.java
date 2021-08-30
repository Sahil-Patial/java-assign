package com.sahil.crud.basiccrud.DAO;

import java.util.*;

import com.sahil.crud.basiccrud.model.User;

public interface UserDAO {
    List<User> getAllUsers();

    User findUserById(int theId);

    User saveUser(User theUser);

    void deleteUserById(int theId);
}
