package com.sahil.crud.basiccrud.service;

import java.util.*;

import com.sahil.crud.basiccrud.model.Book;

public interface BookService {

    public List<Book> findAllBooks();

    public Book findBookById(int theId);

    public Book saveBook(Book theBook);

    public int deleteBookById(int theId);

    // public Book findMaxSoldBookBygenre(String genre);
}
