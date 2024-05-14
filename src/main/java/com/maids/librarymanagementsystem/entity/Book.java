package com.maids.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote entity class represent a books' table record
 */
@Entity
@Table(name = "BOOKS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title of book is required")
    @Column(nullable = false, length = 100, unique = true)
    private String title;

    @NotBlank(message = "Author of book is required")
    @Column(nullable = false, length = 100)
    private String author;

    @NotBlank(message = "Publication year of book is required")
    @Column(nullable = false, length = 4)
    private String publicationYear;

    @NotBlank(message = "isbn of book is required")
    @Size(min = 10, max = 13, message = "isbn should be between 10 and 13 digits")
    @Column(nullable = false, length = 13, unique = true)
    private String isbn;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BorrowingRecord> borrowingRecords;
}
