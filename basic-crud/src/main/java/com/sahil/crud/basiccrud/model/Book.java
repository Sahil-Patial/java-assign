package com.sahil.crud.basiccrud.model;



import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {
    
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="book_id")
    private Integer book_id;

    @Column(name = "book_name")
    private String book_name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author")
    private String author;

    // @OneToMany(mappedBy = "book_id")
    // private Set<Sales> sales;

    @OneToMany(targetEntity = Sales.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private List<Sales> sales;

    public Book(){
    }


    public Book(Integer book_id, String book_name, String genre, String author) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.genre = genre;
        this.author = author;
    }


    public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return this.book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Sales> getSales(){
        return sales;
    }

    public void setSales(List<Sales> sales){
        this.sales = sales;
    }

    

    
}
