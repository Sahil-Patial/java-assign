package com.sahil.crud.basiccrud.model;

import javax.persistence.*;

@Entity
@Table(name = "Sales")
public class Sales {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sales_id")
    private Integer sales_id;

    // @ManyToOne
    // @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    // private User user;

    // @ManyToOne
    // @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    // private Book book;

    public Sales(){

    }

    public Sales(Integer sales_id){

        this.sales_id = sales_id;

    }

    public int getSalesId(){
        return sales_id;
    }

    public void setSalesId(int sales_id){
        this.sales_id = sales_id;
    }

    // public User getUser(){
    //     return user;
    // }
    
    // public void setUser(User user){
    //     this.user = user;
    // }

    // public Book getBook(){
    //     return book;
    // }

    // public void setBook(Book book){
    //     this.book = book;
    // }
    
}
