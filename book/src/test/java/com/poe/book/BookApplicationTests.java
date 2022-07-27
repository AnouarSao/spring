package com.poe.book;

import com.poe.book.business.Book;
import com.poe.book.business.service.BookService;
import com.poe.book.dao.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	BookService bookService;

	@Autowired
	BookRepository bookRepository;

	@Test
	void contextLoads() {

		Book book = new Book();
		book.setTitle("Lord Of The Ring");
		book.setNbPages(3663);
		book.setCategory("Science Fiction");
		bookService.createBook(book);

		Assertions.assertEquals(1, bookService.findAllBooks().size());
	}
	@Test
	void testBookRepository(){
		List<Book> sqlBooks = bookRepository.findAll();
		Assertions.assertEquals(8,sqlBooks.size() );
	}
}
