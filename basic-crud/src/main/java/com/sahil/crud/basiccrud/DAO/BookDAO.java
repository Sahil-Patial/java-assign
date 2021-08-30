package com.sahil.crud.basiccrud.DAO;

import java.util.List;

import com.sahil.crud.basiccrud.model.Book;

public interface BookDAO {
    List<Book> getAllBooks();

    Book findBookById(int theId);

    Book saveBook(Book theBook);

    void deleteBookById(int theId);

    // Book findMaxSoldBookBygenre(String genre);
}
