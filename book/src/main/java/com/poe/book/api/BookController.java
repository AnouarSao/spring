package com.poe.book.api;


import com.poe.book.business.Book;
import com.poe.book.business.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("biblio")
    public void create(@RequestBody Book book){
        bookService.createBook(book);
    }

    @GetMapping("biblio")
    public List<Book> findAll(){
        return bookService.findAllBooks();
    }

    @GetMapping("biblio/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
        Optional<Book> b = bookService.findBookById(id);
        if (b.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(b.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("biblio/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Book book){
        if(! id.equals( book.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'identifiant dans URL ne correspond à l'identifiant dans body");
        } else {
            if(bookService.updateBook(book)){
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucun Client");
            }
        }
    }

    @DeleteMapping("biblio/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
       boolean hasDeleted = bookService.deleteBook(id);
       if(hasDeleted){
           return ResponseEntity.status(HttpStatus.OK).build();
       }else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucun livre !");
       }
    }
}
