package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Book;
import com.maids.librarymanagementsystem.entity.BorrowingRecord;
import com.maids.librarymanagementsystem.entity.Patron;
import com.maids.librarymanagementsystem.excpetions.DuplicateBorrowingException;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.BorrowingRecordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote service class for Borrowing api
 */
@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private final BookService bookService;
    private final PatronService patronService;
    private final LibrarianService librarianService;
    private final BorrowingRecordRepo borrowingRecordRepo;

    /**
     * @implNote  method to retrieve all stored borrowing records
     * @return List of borrowing records
     */
    @Override
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepo.findAll();
    }

    /**
     * @implNote  method to tracking the borrowing operation
     * @param bookId represent id number of target
     * @param patronId represent id number of the patron
     */
    @Override
    public void borrowBook(long bookId, long patronId) {
        Optional<BorrowingRecord> oldRecord = borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(bookId,patronId,"Not Returned");
        if (oldRecord.isEmpty()) {
            Patron patron = patronService.getPatronById(patronId);
            Book book = bookService.getBookById(bookId);
            BorrowingRecord record = new BorrowingRecord(
                    librarianService.getLibrarianById(1),
                    patron, book,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMonths(1),
                    "Not Returned");
            borrowingRecordRepo.save(record);
        }else
            throw new DuplicateBorrowingException("You must return your book first");
    }
    /**
     * @implNote  method to tracking the book returning operation
     * @param bookId represent id number of target
     * @param patronId represent id number of the patron
     */
    @Override
    public void returnBook(long bookId, long patronId) {
        Optional<BorrowingRecord> oldRecord = borrowingRecordRepo.findByBookIdAndPatronIdAndIsReturned(bookId,patronId,"Not Returned");
        if (oldRecord.isPresent()) {
            oldRecord.get().setIsReturned("Returned");
            borrowingRecordRepo.save(oldRecord.get());
        } else
            throw new RecordNotFoundException("Book id or Patron id not found");
    }
}
