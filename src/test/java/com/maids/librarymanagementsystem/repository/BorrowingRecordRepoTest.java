package com.maids.librarymanagementsystem.repository;

import com.maids.librarymanagementsystem.entity.Book;
import com.maids.librarymanagementsystem.entity.BorrowingRecord;
import com.maids.librarymanagementsystem.entity.Librarian;
import com.maids.librarymanagementsystem.entity.Patron;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class that test Borrowing record repository
 */

@SpringBootTest
public class BorrowingRecordRepoTest {
    @MockBean
    @Autowired
    BorrowingRecordRepo borrowingRecordRepo;

    /**
     * @implNote testing for retrieving record which is not existed
     */
    @Test
    public void findByBookIdAndPatronIdAndIsReturnedTestNotFound() {
        Optional<BorrowingRecord> borrowingRecord = borrowingRecordRepo
                .findByBookIdAndPatronIdAndIsReturned(1000L, 1000L, "Not Returned");
        assertFalse(borrowingRecord.isPresent());
    }

    /**
     * @implNote testing for retrieving record which is existed
     */
    @Test
    public void findByBookIdAndPatronIdAndIsReturnedTestFound() {
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

        Optional<BorrowingRecord> borrowingRecord = borrowingRecordRepo
                .findByBookIdAndPatronIdAndIsReturned(1L, 1L, "Not Returned");
        Mockito.when(borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(Optional.of(new BorrowingRecord(1L,
                        librarian.get(),
                        patron.get(),
                        book.get(),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(1),
                        "Not Returned"
                )));
        assertTrue(borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(1L,1L,"Not Returned").isPresent());
    }
}
