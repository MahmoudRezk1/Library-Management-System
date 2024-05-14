package com.maids.librarymanagementsystem.repository;

import com.maids.librarymanagementsystem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class that test user repository
 */
@SpringBootTest
public class UserRepoTest {
    @Autowired
    UserRepo userRepo;

    /**
     * @implNote testing for retrieving user which is not existed
     */
    @Test
    public void findUserByUsernameTestNotFound() {
        Optional<User> user = userRepo.findByUsername("user");
        assertFalse(user.isPresent());
    }

    /**
     * @implNote testing for retrieving user which is existed
     */
    @Test
    public void findUserByUsernameTestFound() {
        Optional<User> user = userRepo.findByUsername("admin");
        assertTrue(user.isPresent());
    }
}
