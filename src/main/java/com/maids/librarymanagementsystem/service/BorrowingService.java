package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.BorrowingRecord;

import java.util.List;

public interface BorrowingService {

    void borrowBook(long bookId, long patronId);
    void returnBook(long bookId, long patronId);
    List<BorrowingRecord> getAllBorrowingRecords();
}
