package com.maids.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote entity class represent a users' table record
 */
@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    @Size(min = 8, max = 500, message = "password not valid")
    private String password;
    @Column(nullable = false)
    private String role;
}
