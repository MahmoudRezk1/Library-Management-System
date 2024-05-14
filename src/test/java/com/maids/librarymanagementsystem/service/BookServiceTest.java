package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Book;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class that test Book service
 */
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @MockBean
    private BookRepo bookRepo;

    /**
     * @implNote testing for retrieving all books which is existed
     */
    @Test
    public void testFindAllBooks() {
        Optional<List<Book>> book = Optional
                .of(Arrays.asList(
                                new Book(1L,
                                        "English",
                                        "mahmoud",
                                        "2020",
                                        "123456789123",
                                        null),

                                new Book(2L,
                                        "Arabic",
                                        "Ahmed",
                                        "2020",
                                        "123456789555",
                                        null)
                        )
                );
        Mockito.when(bookRepo.findAll()).thenReturn(book.get());
        assertNotNull(bookService.getBooks());
    }


    /**
     * @implNote testing for retrieving Book which is existed
     */
    @Test
    public void FindByIdFound() {
        Optional<Book> book = Optional
                .of(new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null));
        Mockito.when(bookRepo.findById(Mockito.anyLong())).thenReturn(book);
        assertDoesNotThrow(() -> bookService.getBookById(1));
    }

    /**
     * @implNote testing for retrieving Book which is not existed
     */
    @Test
    public void FindByIdNotFound() {
        Optional<Book> book = Optional
                .empty();
        Mockito.when(bookRepo.findById(Mockito.anyLong())).thenReturn(book);
        assertThrowsExactly(RecordNotFoundException.class, () -> bookService.getBookById(1000));
    }

    /**
     * @implNote testing for creating Book successfully
     */
    @Test
    public void createBookTest(){
        Optional<Book> book = Optional
                .of(new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null));
        Mockito.when(bookRepo.save(Mockito.any())).thenReturn(book.get());
        assertDoesNotThrow(() -> bookService.createBook(new Book(null,
                "English",
                "mahmoud",
                "2020",
                "123456789123",
                null)));
    }

    /**
     * @implNote testing for updating Book successfully
     */
    @Test
    public void updateBookTest(){
        Optional<Book> book = Optional
                .of(new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null));
        Mockito.when(bookRepo.findById(Mockito.anyLong())).thenReturn(book);
        Mockito.when(bookRepo.save(Mockito.any())).thenReturn(book.get());
        assertDoesNotThrow(() -> bookService.updateBook(new Book(1L,
                "English",
                "mahmoud",
                "2020",
                "123456789123",
                null)));
    }

    /**
     * @implNote testing for updating Book with failed case
     */
    @Test
    public void updateBookFailedTest(){
        Optional<Book> book = Optional
                .of(new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null));
        Mockito.when(bookRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.when(bookRepo.save(Mockito.any())).thenReturn(book.get());
        assertThrowsExactly(RecordNotFoundException.class,() -> bookService.updateBook(new Book(1L,
                "English",
                "mahmoud",
                "2020",
                "123456789123",
                null)));
    }

    /**
     * @implNote testing for deleting Book successfully
     */
    @Test
    public void deleteBookTest(){
        Optional<Book> book = Optional
                .of(new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null));
        Mockito.when(bookRepo.findById(Mockito.anyLong())).thenReturn(book);
        Mockito.doNothing().when(bookRepo).deleteById(Mockito.anyLong());
        assertDoesNotThrow(() -> bookService.deleteBook(1L));
    }
    /**
     * @implNote testing for deleting Book with failed case
     */
    @Test
    public void deleteBookFailedTest(){
        Optional<Book> book = Optional
                .of(new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null));
        Mockito.when(bookRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.doNothing().when(bookRepo).deleteById(Mockito.anyLong());
        assertThrowsExactly(RecordNotFoundException.class,() -> bookService.deleteBook(1L));
    }

}
