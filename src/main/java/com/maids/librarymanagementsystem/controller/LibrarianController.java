package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.entity.Librarian;
import com.maids.librarymanagementsystem.service.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote controller class for librarian api
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/librarians")
public class LibrarianController {
    private final LibrarianService librarianService;
    /**
     * @apiNote api to insert a librarian to our system
     * @return responseEntity with the inserted librarian
     */
    @PostMapping
    public ResponseEntity<Librarian> addLibrarian(@RequestBody Librarian librarian) {
        return ResponseEntity.ok(librarianService.createLibrarian(librarian));
    }
    /**
     * @apiNote api to get a librarian with specific ID
     * @param id represent the id of stored librarian
     * @return responseEntity with the retrieved librarian
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(librarianService.getLibrarianById(id));
    }
}
