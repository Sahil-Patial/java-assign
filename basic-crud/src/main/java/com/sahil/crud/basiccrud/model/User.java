package com.sahil.crud.basiccrud.model;



import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Integer user_id;

    @Column(name="username")
    private String username;

    @OneToMany(targetEntity = Sales.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userID", referencedColumnName = "user_id")
    private List<Sales> sales;

    // @ManyToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
    // @JoinColumn(referencedColumnName = "user_id")
    // private List<Book> books;

    public User(){

    }

    public User(Integer user_id, String username){

        this.user_id = user_id;
        this.username = username;

    }

    public int getId(){
        return user_id;
    }

    public void setId(int user_id){
        this.user_id = user_id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public List<Sales> getSales(){
        return sales;
    }

    public void setSales(List<Sales> sales){
        this.sales = sales;
    }

    // public List<Book> getBook(){
    //     return books;
    // }

    // public void setBook(List<Book> books){
    //     this.books = books;
    // }

}
