package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.entity.Book;
import com.maids.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote controller class for book api
 */
@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    /**
     * @apiNote api to retrieve all stored books
     * @return responseEntity with list of books
     */
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * @apiNote api to retrieve book with specific ID
     * @param id represent the id number of book
     * @return responseEntity of the target book
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    /**
     * @apiNote api to insert book to our system
     * @param book represent the object of target book to store it
     * @return responseEntity of the saved book
     */
    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    /**
     * @apiNote api to update book in our system
     * @param book represent the object of target book to update it
     * @return responseEntity of the updated book
     */
    @PutMapping
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    /**
     * @apiNote api to delete a book from our system
     * @param id represent the id of target book to delete it
     */
    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
