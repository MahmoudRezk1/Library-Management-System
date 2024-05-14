package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Book;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;

    @Override
    @Cacheable(value = "getBooks", key = "#root.methodName")
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @Override
    @Cacheable(value = "getBooksById", key = "#id")
    public Book getBookById(long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else
            throw new RecordNotFoundException("Book with id: " + id + " not found");
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getBooks", "getBooksById"}, key = "#root.methodName", allEntries = true)
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getBooks", "getBooksById"}, key = "#root.methodName", allEntries = true)
    public Book updateBook(Book book) {
        Optional<Book> oldBook = bookRepo.findById(book.getId());
        if (oldBook.isPresent()) {
            oldBook.get().setTitle(book.getTitle());
            oldBook.get().setAuthor(book.getAuthor());
            oldBook.get().setPublicationYear(book.getPublicationYear());
            oldBook.get().setIsbn(book.getIsbn());

            return bookRepo.save(oldBook.get());
        } else
            throw new RecordNotFoundException("Book with id: " + book.getId() + " not found");
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getBooks", "getBooksById"}, key = "#id", allEntries = true)
    public void deleteBook(long id) {
        Optional<Book> oldBook = bookRepo.findById(id);
        if (oldBook.isPresent()) {
            bookRepo.deleteById(id);
        } else
            throw new RecordNotFoundException("Book with id: " + id + " not found");
    }
}