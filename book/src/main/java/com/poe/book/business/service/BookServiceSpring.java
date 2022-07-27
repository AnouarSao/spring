package com.poe.book.business.service;

import com.poe.book.business.Book;
import com.poe.book.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceSpring {

    ArrayList<Book> books = new ArrayList<>();
    private long index = 0;

    @Autowired
    BookRepository bookRepository;

    // CREATE Book
    public void createBook(Book book) {
        index++;
        book.setId(index);
        books.add(book);
    }

    // READ Book
    // READ All
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // READ By ID
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // UPDATE Book
    public boolean update(Book newBook){
        for (Book book : books) {
            if(book.getId().equals(newBook.getId())){
                books.remove(book);
                books.add(newBook);
                return  true;
            }
        }
        return false;
    }

    // DELETE Book
    public boolean deleteBook(Long id){
        for (Book book : books) {
            if(book.getId().equals(id)){
                books.remove(book);
                return true;
            }
        }
        return false;
    }
}