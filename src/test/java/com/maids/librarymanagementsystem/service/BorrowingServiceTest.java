package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Book;
import com.maids.librarymanagementsystem.entity.BorrowingRecord;
import com.maids.librarymanagementsystem.entity.Librarian;
import com.maids.librarymanagementsystem.entity.Patron;
import com.maids.librarymanagementsystem.excpetions.DuplicateBorrowingException;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.BorrowingRecordRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class that test Borrowing service
 */
@SpringBootTest
public class BorrowingServiceTest {

    @Autowired
    BorrowingService borrowingService;
    @MockBean
    BorrowingRecordRepo borrowingRecordRepo;
    @MockBean
    BookService bookService;
    @MockBean
    PatronService patronService;
    @MockBean
    LibrarianService librarianService;

    /**
     * @implNote testing for borrowing a book successfully
     */
    @Test
    public void borrowTest() {
        Optional<Book> book = Optional.of(new Book(1L,
                "English",
                "mahmoud",
                "2020",
                "123456789123",
                null));
        Optional<Patron> patron = Optional.of(new Patron(1L,
                "test",
                "test",
                "test@test.com",
                "01234567890",
                "testaddress",
                "test",
                "test",
                null));
        Optional<Librarian> librarian = Optional.of(new Librarian(1L, "test",
                "test", "test@test.com",
                "012345678911",
                null));
        Optional<BorrowingRecord> borrowingRecord = Optional.of(new BorrowingRecord(1L,
                librarian.get(),
                patron.get(),
                book.get(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(1),
                "Returned"
        ));
        Mockito.when(borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(1L, 1L, "Returned")).thenReturn(borrowingRecord);
        Mockito.when(bookService.getBookById(Mockito.anyLong())).thenReturn(book.get());
        Mockito.when(patronService.getPatronById(Mockito.anyLong())).thenReturn(patron.get());
        Mockito.when(librarianService.getLibrarianById(Mockito.anyLong())).thenReturn(librarian.get());
        Mockito.when(borrowingRecordRepo.save(Mockito.any())).thenReturn(borrowingRecord.get());
        assertDoesNotThrow(() -> borrowingService.borrowBook(1L, 1L));
    }

    /**
     * @implNote testing for borrowing a book case of failed
     */
    @Test
    public void borrowFailedTest() {
        Optional<BorrowingRecord> borrowingRecord = Optional.of(new BorrowingRecord(1L,
                new Librarian(1L, "test",
                        "test", "test@test.com",
                        "012345678911",
                        null),
                new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null),
                new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null),
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(1),
                "Not Returned"
        ));
        Mockito.when(borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(1L, 1L, "Not Returned")).thenReturn(borrowingRecord);
        Mockito.when(borrowingRecordRepo.save(Mockito.any())).thenReturn(borrowingRecord.get());
        assertThrowsExactly(DuplicateBorrowingException.class, () -> borrowingService.borrowBook(1L, 1L));
    }


    /**
     * @implNote testing for returning a book successfully
     */
    @Test
    public void returnBookTest() {
        Optional<BorrowingRecord> borrowingRecord = Optional.of(new BorrowingRecord(1L,
                new Librarian(1L, "test",
                        "test", "test@test.com",
                        "012345678911",
                        null),
                new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null),
                new Book(1L,
                        "English",
                        "mahmoud",
                        "2020",
                        "123456789123",
                        null),
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(1),
                "Not Returned"
        ));
        Mockito.when(borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(1L, 1L, "Not Returned")).thenReturn(borrowingRecord);
        Mockito.when(borrowingRecordRepo.save(Mockito.any())).thenReturn(borrowingRecord.get());
        assertDoesNotThrow(() -> borrowingService.returnBook(1L, 1L));
    }

    /**
     * @implNote testing for returning a book case of failed
     */
    @Test
    public void returnBookFailedTest() {
        Optional<BorrowingRecord> borrowingRecord = Optional.empty();
        Mockito.when(borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(1L, 1L, "Returned")).thenReturn(borrowingRecord);
        Mockito.when(borrowingRecordRepo.save(Mockito.any())).thenReturn(borrowingRecord);
        assertThrowsExactly(RecordNotFoundException.class, () -> borrowingService.returnBook(1L, 1L));
    }
}
