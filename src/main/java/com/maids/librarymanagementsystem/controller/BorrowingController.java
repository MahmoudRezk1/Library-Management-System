package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote controller class for borrowing and returning book api
 */
@RestController
@RequestMapping(path = "/api/borrow")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    /**
     * @param bookId   represent the id of the target book
     * @param patronId represent the id of the patron
     * @apiNote api to borrow a book with its specific ID and patron id
     */
    @PostMapping(path = "/{bookId}/patron/{patronId}")
    public void borrow(@PathVariable long bookId, @PathVariable long patronId) {
        borrowingService.borrowBook(bookId, patronId);
    }

    /**
     * @param bookId   represent the id of the target book
     * @param patronId represent the id of the patron
     * @apiNote api to retrieve a book with its specific ID and patron id
     */
    @PutMapping(path = "/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable long bookId, @PathVariable long patronId) {
        borrowingService.returnBook(bookId, patronId);
    }

    /**
     * @return responseEntity represent a list of retrieved records
     * @apiNote api to retrieve all record of borrowing and retrieving record
     */
    @GetMapping
    public ResponseEntity<?> getBorrowedBooks() {
        return ResponseEntity.ok(borrowingService.getAllBorrowingRecords());
    }
}
