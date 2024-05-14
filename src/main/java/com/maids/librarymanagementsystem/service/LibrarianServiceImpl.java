package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Librarian;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.LibrarianRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService{
    private final LibrarianRepo librarianRepo;

    @Override
    @Cacheable(value = "getLibrarians", key = "#root.methodName")
    public List<Librarian> getLibrarians() {
        return librarianRepo.findAll();
    }

    @Override
    @Cacheable(value = "getLibrariansById", key = "#id")
    public Librarian getLibrarianById(long id) {
        Optional<Librarian> librarian = librarianRepo.findById(id);
        if (librarian.isPresent()) {
            return librarian.get();
        } else
            throw new RecordNotFoundException("Librarian with id: " + id + " not found");
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getLibrarians", "getLibrariansById"}, key = "#root.methodName", allEntries = true)
    public Librarian createLibrarian(Librarian librarian) {
        return librarianRepo.save(librarian);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getLibrarians", "getLibrariansById"}, key = "#root.methodName", allEntries = true)
    public Librarian updateLibrarian(Librarian librarian) {
        Optional<Librarian> oldLibrarian = librarianRepo.findById(librarian.getId());
        if (oldLibrarian.isPresent()) {
            oldLibrarian.get().setFirstName(librarian.getFirstName());
            oldLibrarian.get().setLastName(librarian.getLastName());
            oldLibrarian.get().setPhone(librarian.getPhone());
            oldLibrarian.get().setEmail(librarian.getEmail());

            return librarianRepo.save(oldLibrarian.get());
        } else
            throw new RecordNotFoundException("Librarian with id: " + librarian.getId() + " not found");
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getLibrarians", "getLibrariansById"}, key = "#id", allEntries = true)
    public void deleteLibrarian(long id) {
        Optional<Librarian> oldLibrarian = librarianRepo.findById(id);
        if (oldLibrarian.isPresent()) {
            librarianRepo.deleteById(id);
        } else
            throw new RecordNotFoundException("Librarian with id: " + id + " not found");
    }
}
