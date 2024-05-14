package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Patron;

import java.util.List;

public interface PatronService {
    List<Patron> getPatrons();
    Patron getPatronById(long id);
    Patron createPatron(Patron patron);
    Patron updatePatron(Patron patron);
    void deletePatron(long id);
}
