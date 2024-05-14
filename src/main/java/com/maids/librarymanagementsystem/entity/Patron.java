package com.maids.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote entity class represent a patrons' table record
 */
@Entity
@Table(name = "PATRONS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "E-mail is required")
    @Email(message = "E-mail is not valid")
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "0\\d{10}", message = "phone number must start with 0 and contains 11 numbers")
    @Column(nullable = false, length = 20, unique = true)
    private String phone;

    @NotBlank(message = "Address required")
    @Column(nullable = false,length = 255)
    private String address;

    @NotBlank(message = "City required")
    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String state;
    @OneToMany(mappedBy = "patron")
    @JsonIgnore
    private List<BorrowingRecord> borrowingRecords;
}
