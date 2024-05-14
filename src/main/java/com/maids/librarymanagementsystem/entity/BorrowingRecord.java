package com.maids.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote entity class represent a borrowing_records' table record
 */
@Entity
@Table(name = "BORROWING_RECORDS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "librarian_id",referencedColumnName = "id")
    private Librarian librarian;

    @ManyToOne
    @JoinColumn(name = "patron_id",referencedColumnName = "id")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime borrowingDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDate;

    @Column(nullable = false,length = 15)
    private String isReturned;

    public BorrowingRecord(Librarian librarian, Patron patron, Book book, LocalDateTime borrowingDate, LocalDateTime returnDate, String isReturned) {
        this.librarian = librarian;
        this.patron = patron;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }
}
