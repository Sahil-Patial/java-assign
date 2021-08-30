package com.sahil.crud.basiccrud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import com.sahil.crud.basiccrud.DAO.BookDAO;
import com.sahil.crud.basiccrud.model.Book;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class BasicCrudApplicationTests {

	@Autowired
	private BookDAO repo;


	@Test
	@Order(1)
	@Transactional
	@Rollback(false)
	public void testSaveBook() {

		Book b = new Book();
		b.setBook_id(15);
		b.setBook_name("Sample");
		b.setAuthor("XYZ");
		b.setGenre("gen");
		
		repo.saveBook(b);
		assertNotNull(repo.findBookById(15).getBook_id());
		
	}
	
	
	@Test
	@Order(2)
	public void testGetAllBooks() {

		List<Book> book = repo.getAllBooks();
		assertThat(book).size().isGreaterThan(0);
		
	}

	@Test
	@Order(3)
	public void testFindBookByID(){
		Book book = repo.findBookById(7);
		assertThat(book.getBook_id()==7);
	}

	@Test
	@Order(4)
	@Transactional
	@Rollback(false)
	public void testUpdateBook(){
		Book b = repo.findBookById(15);
		b.setBook_name("Book");

		repo.saveBook(b);
		Book ub = repo.findBookById(15);
		assertThat(ub.getBook_name()).isEqualTo("Book");
	}

	@Test
	@Order(5)
	@Transactional
	@Rollback(false)
	public void testDeleteBook() {
		Book b = repo.findBookById(15);
		repo.deleteBookById(b.getBook_id());
		
		assertNotNull(repo.findBookById(b.getBook_id()).getBook_id());
		

	}

}
