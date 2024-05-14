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

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {
    private final PatronRepo patronRepo;

    @Override
    @Cacheable(value = "getPatrons", key = "#root.methodName")
    public List<Patron> getPatrons() {
        return patronRepo.findAll();
    }

    @Override
    @Cacheable(value = "getPatronById", key = "#id")
    public Patron getPatronById(long id) {
        Optional<Patron> patron = patronRepo.findById(id);
        if (patron.isPresent()) {
            return patron.get();
        } else
            throw new RecordNotFoundException("Patron with id: " + id + " not found");
    }

    @Override
    @Transactional
    @CacheEvict(value = {"getPatrons","getPatronById"}, key = "#root.methodName", allEntries = true)
    public Patron createPatron(Patron patron) {
        return patronRepo.save(patron);
    }

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
