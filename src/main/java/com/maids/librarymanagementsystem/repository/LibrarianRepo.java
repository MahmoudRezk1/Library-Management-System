package com.maids.librarymanagementsystem.repository;

import com.maids.librarymanagementsystem.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepo extends JpaRepository<Librarian, Long> {
}
