package com.sahil.crud.basiccrud.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import com.sahil.crud.basiccrud.model.User;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOJpaImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    //get all the users from database
    @Override
    public List<User> getAllUsers() {
        Query theQuery = (Query) entityManager.createQuery("from User");
        List<User> users = theQuery.getResultList();

        return users;
    }

    //return the user by giving id as input
    @Override
    public User findUserById(int theId) {
        User theUser = entityManager.find(User.class, theId);
        return theUser;
    }

    //add the user to the database
    @Override
    public User saveUser(User theUser) {
        User dbUser = entityManager.merge(theUser);
        theUser.setId(dbUser.getId());
        return theUser;
    }

    //delete the user from the database using user_id
    @Override
    public void deleteUserById(int theId) {
        Query theQuery = (Query)entityManager.createQuery("delete from User where id=:userId");
        theQuery.setParameter("userId", theId);
        theQuery.executeUpdate();

        
    }

    

}
