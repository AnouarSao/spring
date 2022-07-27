package com.poe.book;

import com.poe.book.business.Book;
import com.poe.book.business.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JUnitTests {


	@Test
	void testAddBook() {
		BookService bookService = new BookService();
		Book book = new Book();
		book.setTitle("Lord Of The Ring");
		book.setNbPages(3663);
		book.setCategory("Science Fiction");
		bookService.createBook(book);

		Assertions.assertEquals(1, bookService.findAllBooks().size());
	}
}

