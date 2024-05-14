package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Patron;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.PatronRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class that test Patron service
 */
@SpringBootTest
public class PatronServiceTest {
    @Autowired
    private PatronService patronService;
    @MockBean
    private PatronRepo patronRepo;

    /**
     * @implNote testing for retrieving all Patrons which is existed
     */
    @Test
    public void testFindAllPatrons() {
        Optional<List<Patron>> book = Optional
                .of(Arrays.asList(
                                new Patron(1L,
                                        "test",
                                        "test",
                                        "test@test.com",
                                        "01234567890",
                                        "testaddress",
                                        "test",
                                        "test",
                                        null),

                                new Patron(1L,
                                        "test",
                                        "test",
                                        "test2@test.com",
                                        "01234567891",
                                        "testaddress",
                                        "test",
                                        "test",
                                        null)
                        )
                );
        Mockito.when(patronRepo.findAll()).thenReturn(book.get());
        assertNotNull(patronService.getPatrons());
    }


    /**
     * @implNote testing for retrieving patron which is existed
     */
    @Test
    public void FindByIdFound() {
        Optional<Patron> patron = Optional
                .of(new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null));
        Mockito.when(patronRepo.findById(Mockito.anyLong())).thenReturn(patron);
        assertDoesNotThrow(() -> patronService.getPatronById(1));
    }

    /**
     * @implNote testing for retrieving patron which is not existed
     */
    @Test
    public void FindByIdNotFound() {
        Optional<Patron> patron = Optional
                .empty();
        Mockito.when(patronRepo.findById(Mockito.anyLong())).thenReturn(patron);
        assertThrowsExactly(RecordNotFoundException.class, () -> patronService.getPatronById(1000));
    }

    /**
     * @implNote testing for creating patron successfully
     */
    @Test
    public void createPatronTest() {
        Optional<Patron> patron = Optional
                .of(new Patron(null,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null));
        Mockito.when(patronRepo.save(Mockito.any())).thenReturn(patron.get());
        assertDoesNotThrow(() -> patronService.createPatron(new Patron(1L,
                "test",
                "test",
                "test@test.com",
                "01234567890",
                "testaddress",
                "test",
                "test",
                null)));
    }

    /**
     * @implNote testing for updating patron successfully
     */
    @Test
    public void updatePatronTest() {
        Optional<Patron> patron = Optional
                .of(new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null));
        Mockito.when(patronRepo.findById(Mockito.anyLong())).thenReturn(patron);
        Mockito.when(patronRepo.save(Mockito.any())).thenReturn(patron.get());
        assertDoesNotThrow(() -> patronService.updatePatron(new Patron(1L,
                "test",
                "test",
                "test@test.com",
                "01234567890",
                "testaddress",
                "test",
                "test",
                null)));
    }

    /**
     * @implNote testing for updating patron with failed case
     */
    @Test
    public void updatePatronFailedTest() {
        Optional<Patron> patron = Optional
                .of(new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null));
        Mockito.when(patronRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.when(patronRepo.save(Mockito.any())).thenReturn(patron.get());
        assertThrowsExactly(RecordNotFoundException.class, () -> patronService.updatePatron(new Patron(1L,
                "test",
                "test",
                "test@test.com",
                "01234567890",
                "testaddress",
                "test",
                "test",
                null)));
    }

    /**
     * @implNote testing for deleting patron successfully
     */
    @Test
    public void deletePatronTest() {
        Optional<Patron> patron = Optional
                .of(new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null));
        Mockito.when(patronRepo.findById(Mockito.anyLong())).thenReturn(patron);
        Mockito.doNothing().when(patronRepo).deleteById(Mockito.anyLong());
        assertDoesNotThrow(() -> patronService.deletePatron(1L));
    }

    /**
     * @implNote testing for deleting patron with failed case
     */
    @Test
    public void deletePatronFailedTest() {
        Optional<Patron> patron = Optional
                .of(new Patron(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567890",
                        "testaddress",
                        "test",
                        "test",
                        null));
        Mockito.when(patronRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.doNothing().when(patronRepo).deleteById(Mockito.anyLong());
        assertThrowsExactly(RecordNotFoundException.class, () -> patronService.deletePatron(1L));
    }
}
