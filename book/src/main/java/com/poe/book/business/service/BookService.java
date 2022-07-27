package com.poe.book.business.service;

import com.poe.book.business.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    ArrayList<Book> books = new ArrayList<>();
    private long index = 0;

    // CREATE Book
    public void createBook(Book book) {
        index++;
        book.setId(index);
        books.add(book);
    }

    // READ Book
    // READ All
    public List<Book> findAllBooks() {
        return books;
    }

    // READ By ID
    public Optional<Book> findBookById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    // UPDATE Book
    public boolean updateBook(Book newBook){
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