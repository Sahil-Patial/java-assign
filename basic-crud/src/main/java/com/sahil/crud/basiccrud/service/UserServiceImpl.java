package com.sahil.crud.basiccrud.service;

import java.util.Arrays;
import java.util.List;

import com.sahil.crud.basiccrud.DAO.UserDAO;
import com.sahil.crud.basiccrud.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserServiceImpl implements UserService{

    UserDAO userDAO;
    private static final String[] blackListUsernames = new String[]{"user1","user2","user3","user4"};

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOJpaImpl") UserDAO theUserDAO){

        userDAO = theUserDAO;

    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public String screenUserById(int theId) {
        User theUser = userDAO.findUserById(theId);
        boolean isInBlackList = Arrays.asList(blackListUsernames).contains(theUser.getName());

        //return Reject if username in blacklist, otherwise accept
        if(isInBlackList){
            return "REJECT";
        }
        else{
            return "ACCEPT";
        }
    }

    @Override
    @Transactional
    public User findUserById(int theId) {
        return userDAO.findUserById(theId);
    }

    @Override
    @Transactional
    public User saveUser(User theUser) {
        return userDAO.saveUser(theUser);
    }

    @Override
    @Transactional
    public int deleteUserById(int theId) {
        userDAO.deleteUserById(theId);
        return theId;
    }
    
}
