package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(long id);
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBook(long id);
}
