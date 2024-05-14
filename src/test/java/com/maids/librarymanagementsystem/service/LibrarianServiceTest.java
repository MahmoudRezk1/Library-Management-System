package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.Librarian;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.LibrarianRepo;
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
 * @implNote class that test Librarian service
 */
@SpringBootTest
public class LibrarianServiceTest {
    @Autowired
    private LibrarianService librarianService;
    @MockBean
    private LibrarianRepo librarianRepo;

    /**
     * @implNote testing for retrieving all librarians which is existed
     */
    @Test
    public void testFindAllLibrarians() {
        Optional<List<Librarian>> librarians = Optional
                .of(Arrays.asList(
                                new Librarian(1L,
                                        "test",
                                        "test",
                                        "test@test.com",
                                        "01234567891",
                                        null),

                                new Librarian(1L,
                                        "test",
                                        "test",
                                        "test@test.com",
                                        "01234567891",
                                        null)
                        )
                );
        Mockito.when(librarianRepo.findAll()).thenReturn(librarians.get());
        assertNotNull(librarianService.getLibrarians());
    }


    /**
     * @implNote testing for retrieving librarian which is existed
     */
    @Test
    public void FindByIdFound() {
        Optional<Librarian> librarian = Optional
                .of(new Librarian(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567891",
                        null));
        Mockito.when(librarianRepo.findById(Mockito.anyLong())).thenReturn(librarian);
        assertDoesNotThrow(() -> librarianService.getLibrarianById(1));
    }

    /**
     * @implNote testing for retrieving librarian which is not existed
     */
    @Test
    public void FindByIdNotFound() {
        Optional<Librarian> librarian = Optional
                .empty();
        Mockito.when(librarianRepo.findById(Mockito.anyLong())).thenReturn(librarian);
        assertThrowsExactly(RecordNotFoundException.class, () -> librarianService.getLibrarianById(1000));
    }

    /**
     * @implNote testing for creating librarian successfully
     */
    @Test
    public void createLibrarianTest() {
        Optional<Librarian> librarian = Optional
                .of(new Librarian(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567891",
                        null));
        Mockito.when(librarianRepo.save(Mockito.any())).thenReturn(librarian.get());
        assertDoesNotThrow(() -> librarianService.createLibrarian(new Librarian(null,
                "test",
                "test",
                "test@test.com",
                "01234567891",
                null)));
    }

    /**
     * @implNote testing for updating librarian successfully
     */
    @Test
    public void updateLibrarianTest() {
        Optional<Librarian> librarian = Optional
                .of(new Librarian(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567891",
                        null));
        Mockito.when(librarianRepo.findById(Mockito.anyLong())).thenReturn(librarian);
        Mockito.when(librarianRepo.save(Mockito.any())).thenReturn(librarian.get());
        assertDoesNotThrow(() -> librarianService.updateLibrarian(new Librarian(1L,
                "test",
                "test",
                "test@test.com",
                "01234567891",
                null)));
    }

    /**
     * @implNote testing for updating librarian with failed case
     */
    @Test
    public void updateLibrarianFailedTest() {
        Optional<Librarian> librarian = Optional
                .of(new Librarian(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567891",
                        null));
        Mockito.when(librarianRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.when(librarianRepo.save(Mockito.any())).thenReturn(librarian.get());
        assertThrowsExactly(RecordNotFoundException.class, () -> librarianService.updateLibrarian(new Librarian(1L,
                "test",
                "test",
                "test@test.com",
                "01234567891",
                null)));
    }

    /**
     * @implNote testing for deleting librarian successfully
     */
    @Test
    public void deleteLibrarianTest() {
        Optional<Librarian> librarian = Optional
                .of(new Librarian(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567891",
                        null));
        Mockito.when(librarianRepo.findById(Mockito.anyLong())).thenReturn(librarian);
        Mockito.doNothing().when(librarianRepo).deleteById(Mockito.anyLong());
        assertDoesNotThrow(() -> librarianService.deleteLibrarian(1L));
    }

    /**
     * @implNote testing for deleting librarian with failed case
     */
    @Test
    public void deleteLibrarianFailedTest() {
        Optional<Librarian> librarian = Optional
                .of(new Librarian(1L,
                        "test",
                        "test",
                        "test@test.com",
                        "01234567891",
                        null));
        Mockito.when(librarianRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.doNothing().when(librarianRepo).deleteById(Mockito.anyLong());
        assertThrowsExactly(RecordNotFoundException.class, () -> librarianService.deleteLibrarian(1L));
    }
}
