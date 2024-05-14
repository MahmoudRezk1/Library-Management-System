package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.entity.Patron;
import com.maids.librarymanagementsystem.service.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author mahmoudrezk514@gmail.com
 * @implNote controller class for patron api
 */
@RestController
@RequestMapping(path = "/api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;

    /**
     * @apiNote api to retrieve all stored patrons
     * @return responseEntity with list of patrons
     */
    @GetMapping
    public ResponseEntity<?> getAllPatrons() {
        List<Patron> patrons = patronService.getPatrons();
        return ResponseEntity.ok(patrons);
    }

    /**
     * @apiNote api to retrieve patron with specific ID
     * @param id represent the id number of patron
     * @return responseEntity of the target patron
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatronById(id);
        return ResponseEntity.ok(patron);
    }

    /**
     * @apiNote api to insert patron to our system
     * @param patron represent the object of target patron to store it
     * @return responseEntity of the saved patron
     */
    @PostMapping
    public ResponseEntity<?> createPatron(@Valid @RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.createPatron(patron));
    }

    /**
     * @apiNote api to update patron in our system
     * @param patron represent the object of target patron to update it
     * @return responseEntity of the updated patron
     */
    @PutMapping
    public ResponseEntity<?> updatePatron(@Valid @RequestBody Patron patron) {
        return ResponseEntity.ok(patronService.updatePatron(patron));
    }

    /**
     * @apiNote api to delete a patron from our system
     * @param id represent the id of target patron to delete it
     */
    @DeleteMapping(path = "/{id}")
    public void deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
    }
}
