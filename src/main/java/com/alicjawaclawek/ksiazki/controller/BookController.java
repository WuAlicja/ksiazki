package com.alicjawaclawek.ksiazki.controller;

import com.alicjawaclawek.ksiazki.model.Book;
import com.alicjawaclawek.ksiazki.model.Category;
import com.alicjawaclawek.ksiazki.repository.BookRepository;
import com.alicjawaclawek.ksiazki.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//nasze api bedzie dostepne dla innych - CrossOrigin
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

    private BookRepository bookRepository;

    @Autowired /* nie wymagane */
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("books")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @PostMapping("books")
    public Book createBook(@RequestParam String title, @RequestParam String author, @RequestParam(required = false) Integer categoryId) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        return bookRepository.save(book);
    }

    //required=false, daje mozliwosc nie podawania danego parametru(np author) i wywolania takiej metody
    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestParam String title,
                                           @RequestParam(required = false) String author, @RequestParam(required = false) Integer categoryId) {

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setAuthor(author);
            book.setTitle(title);
            book.setCategoryId(categoryId);
            //save zapisuje do bazy jesli nie istnieje, a jesli istnije to robi update
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {

        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
