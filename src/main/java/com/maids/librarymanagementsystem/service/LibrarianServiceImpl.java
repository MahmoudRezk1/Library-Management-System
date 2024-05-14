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

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote service class for librarian api
 */
@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService{
    private final LibrarianRepo librarianRepo;

    /**
     * @implNote  method to retrieve all stored librarians
     * and caching the retrieved data after calling
     * @return List of librarians
     */
    @Override
    @Cacheable(value = "getLibrarians", key = "#root.methodName")
    public List<Librarian> getLibrarians() {
        return librarianRepo.findAll();
    }

    /**
     * @apiNote method to retrieve librarian with specific ID
     * and caching the retrieved data after calling
     * @param id represent the id number of librarian
     * @return librarian object of the target librarian
     */
    @Override
    @Cacheable(value = "getLibrariansById", key = "#id")
    public Librarian getLibrarianById(long id) {
        Optional<Librarian> librarian = librarianRepo.findById(id);
        if (librarian.isPresent()) {
            return librarian.get();
        } else
            throw new RecordNotFoundException("Librarian with id: " + id + " not found");
    }

    /**
     * @implNote  method to insert librarian to our system
     * and clear the caching data after calling
     * @param librarian represent the object of target librarian to store it
     * @return librarian object of the saved librarian
     */
    @Override
    @Transactional
    @CacheEvict(value = {"getLibrarians", "getLibrariansById"}, key = "#root.methodName", allEntries = true)
    public Librarian createLibrarian(Librarian librarian) {
        return librarianRepo.save(librarian);
    }

    /**
     * @implNote  method to update librarian in our system
     * and clear the caching data after calling
     * @param librarian represent the object of target librarian to update it
     * @return librarian object of the updated librarian
     */
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

    /**
     * @implNote  method to delete a librarian from our system
     * and clear the caching data after calling
     * @param id represent the id of target librarian to delete it
     */
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
