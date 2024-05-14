package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Patron;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.PatronRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote service class for patron api
 */
@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepo patronRepo;

    /**
     * @implNote  method to retrieve all stored patrons
     * and caching the retrieved data after calling
     * @return List of patrons
     */
    @Override
    @Cacheable(value = "getPatrons", key = "#root.methodName")
    public List<Patron> getPatrons() {
        return patronRepo.findAll();
    }

    /**
     * @implNote  method to retrieve patron with specific ID
     * and caching the retrieved data after calling
     * @param id represent the id number of patron
     * @return patron object of the target patron
     */
    @Override
    @Cacheable(value = "getPatronById", key = "#id")
    public Patron getPatronById(long id) {
        Optional<Patron> patron = patronRepo.findById(id);
        if (patron.isPresent()) {
            return patron.get();
        } else
            throw new RecordNotFoundException("Patron with id: " + id + " not found");
    }

    /**
     * @implNote  method to insert patron to our system
     * and clear the caching data after calling
     * @param patron represent the object of target patron to store it
     * @return patron object of the saved patron
     */
    @Override
    @Transactional
    @CacheEvict(value = {"getPatrons","getPatronById"}, key = "#root.methodName", allEntries = true)
    public Patron createPatron(Patron patron) {
        return patronRepo.save(patron);
    }

    /**
     * @implNote  method to update patron in our system
     * and clear the caching data after calling
     * @param patron represent the object of target patron to update it
     * @return patron object of the updated patron
     */
    @Override
    @Transactional
    @CacheEvict(value = {"getPatrons","getPatronById"}, key = "#root.methodName", allEntries = true)
    public Patron updatePatron(Patron patron) {
        Optional<Patron> oldPatron = patronRepo.findById(patron.getId());
        if (oldPatron.isPresent()) {
            oldPatron.get().setFirstName(patron.getFirstName());
            oldPatron.get().setLastName(patron.getLastName());
            oldPatron.get().setEmail(patron.getEmail());
            oldPatron.get().setPhone(patron.getPhone());
            oldPatron.get().setAddress(patron.getAddress());
            oldPatron.get().setCity(patron.getCity());
            oldPatron.get().setState(patron.getState());

            return patronRepo.save(oldPatron.get());
        } else
            throw new RecordNotFoundException("Patron with id: " + patron.getId() + " not found");
    }

    /**
     * @implNote  method to delete a patron from our system
     * and clear the caching data after calling
     * @param id represent the id of target patron to delete it
     */
    @Override
    @Transactional
    @CacheEvict(value = {"getPatrons","getPatronById"}, key = "#id", allEntries = true)
    public void deletePatron(long id) {
        Optional<Patron> oldPatron = patronRepo.findById(id);
        if (oldPatron.isPresent()) {
            patronRepo.deleteById(id);
        } else
            throw new RecordNotFoundException("Patron with id: " + id + " not found");
    }
}
