package com.sahil.crud.basiccrud.controller;

import java.util.List;

import com.sahil.crud.basiccrud.model.User;
import com.sahil.crud.basiccrud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/demo")
public class UserController {

    private UserService userService;
    
    

    @Autowired
    public UserController(UserService theuserservice){
        userService = theuserservice;
    }
    

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        System.out.println(userService.findAllUsers().size());
        return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String screenUser(@PathVariable int userId)  {
        String theUser = userService.screenUserById(userId);

        return theUser;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User addUser(@RequestBody User theUser){
        return(userService.saveUser(theUser));
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User updatUser(@RequestBody User theUser){
        User user = userService.findUserById(theUser.getId());

        if(user == null) {
            throw new RuntimeException("User to update doesn't exist");
        }

        return (userService.saveUser(theUser));
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int userId){
        User tempUser = userService.findUserById(userId);

        if(tempUser == null){
            throw new RuntimeException("User Id not found");
        }
        userService.deleteUserById(userId);

        return "Deleted user id " + userId;
    }
    

    
}
