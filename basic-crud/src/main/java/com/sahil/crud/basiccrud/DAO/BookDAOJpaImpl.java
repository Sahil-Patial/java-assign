package com.sahil.crud.basiccrud.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.sahil.crud.basiccrud.model.Book;
import com.sahil.crud.basiccrud.model.Sales;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOJpaImpl implements BookDAO{

    private EntityManager entityManager;

    @Autowired
    public BookDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> getAllBooks() {
        Query theQuery = (Query) entityManager.createQuery("from Book");
        List<Book> books = theQuery.getResultList();

        return books;
    }

    @Override
    public Book findBookById(int theId) {
        Book theBook = entityManager.find(Book.class, theId);
        return theBook;
    }

    @Override
    public Book saveBook(Book theBook) {
        Book dbBook = entityManager.merge(theBook);
        theBook.setBook_id(dbBook.getBook_id());
        return theBook;
    }

    @Override
    public void deleteBookById(int theId) {
        Query theQuery = (Query) entityManager.createQuery("delete from Book where id=:bookId");
        theQuery.setParameter("bookId", theId);
        theQuery.executeUpdate();
        
    }

    
    
    
}
