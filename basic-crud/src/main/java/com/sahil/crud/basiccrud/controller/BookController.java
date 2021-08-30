package com.sahil.crud.basiccrud.controller;

import java.util.List;

import com.sahil.crud.basiccrud.model.Book;
import com.sahil.crud.basiccrud.service.BookService;

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
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService thebookservice){
        bookService = thebookservice;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAllb(){
        System.out.println(bookService.findAllBooks().size());
        return new ResponseEntity<List<Book>>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book theBook){
        return(bookService.saveBook(theBook));
    }

    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book theBook){
        Book book = bookService.findBookById(theBook.getBook_id());

        if(book == null) {
            throw new RuntimeException("Book to update doesn't exist");
        }

        return (bookService.saveBook(theBook));
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable int bookId){
        Book tempBook = bookService.findBookById(bookId);

        if(tempBook == null){
            throw new RuntimeException("Book Id not found");
        }
        bookService.deleteBookById(bookId);

        return "Deleted book id " + bookId;
    }

    // @RequestMapping(value = "/books/maxsold/{Genre}", method = RequestMethod.GET)
    // public Book maxsoldBook(@PathVariable String Genre){
    //     Book tBook = bookService.findMaxSoldBookBygenre(Genre);

    //     if(tBook == null){
    //         throw new RuntimeException("Book genre not found");
    //     }
        
    //     return (bookService.findMaxSoldBookBygenre(Genre));
    // }

    
}
