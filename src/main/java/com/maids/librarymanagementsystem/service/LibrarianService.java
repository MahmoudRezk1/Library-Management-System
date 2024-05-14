package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Librarian;

import java.util.List;

public interface LibrarianService {
    List<Librarian> getLibrarians();
    Librarian getLibrarianById(long id);
    Librarian createLibrarian(Librarian librarian);
    Librarian updateLibrarian( Librarian librarian);
    void deleteLibrarian(long id);
}
