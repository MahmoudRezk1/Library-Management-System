package com.maids.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote entity class represent a librarians' table record
 */
@Entity
@Table(name = "LIBRARIANS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 20, unique = true)
    private String phone;

    @OneToMany(mappedBy = "librarian")
    @JsonIgnore
    private List<BorrowingRecord> borrowingRecords;
}
