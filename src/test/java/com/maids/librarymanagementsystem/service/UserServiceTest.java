package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.User;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.UserRepo;
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
 * @implNote class that test user service
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepo userRepo;

    /**
     * @implNote testing for retrieving all users which is existed
     */
    @Test
    public void testFindAllUsers() {
        Optional<List<User>> users = Optional
                .of(Arrays.asList(
                                new User(1L,
                                        "test123",
                                        "test123",
                                        "ROLE_ADMIN"),

                                new User(1L,
                                        "test456",
                                        "test456",
                                        "ROLE_USER")
                        )
                );
        Mockito.when(userRepo.findAll()).thenReturn(users.get());
        assertNotNull(userService.findAllUsers());
    }

    /**
     * @implNote testing for finding user by username successfully
     */
    @Test
    public void findUserByUsernameTest() {
        Optional<User> user = Optional
                .of(new User(null,
                        "test123",
                        "test123",
                        "ROLE_ADMIN"));
        Mockito.when(userRepo.findByUsername(Mockito.anyString())).thenReturn(user);
        assertNotNull(userService.findUserByUsername("test123"));
    }

    /**
     * @implNote testing for retrieving user which is not existed
     */
    @Test
    public void FindByUsernameTestNotFound() {
        Optional<User> user = Optional
                .empty();
        Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn(user);
        assertThrowsExactly(RecordNotFoundException.class, () -> userService.findUserByUsername("user"));
    }

    /**
     * @implNote testing for creating user successfully
     */
    @Test
    public void createUserTest() {
        Optional<User> user = Optional
                .of(new User(null,
                        "test123",
                        "test123",
                        "ROLE_ADMIN"));
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user.get());
        assertDoesNotThrow(() -> userService.createUser(new User(1L,
                "test123",
                "test123",
                "ROLE_ADMIN")));
    }

    /**
     * @implNote testing for updating user successfully
     */
    @Test
    public void updateUserTest() {
        Optional<User> user = Optional
                .of(new User(1L,
                        "test123",
                        "test123",
                        "ROLE_ADMIN"));
        Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn(user);
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user.get());
        assertDoesNotThrow(() -> userService.updateUser(new User(1L,
                "test123",
                "test123",
                "ROLE_ADMIN")));
    }

    /**
     * @implNote testing for updating user with failed case
     */
    @Test
    public void updateUserFailedTest() {
        Optional<User> user = Optional
                .of(new User(1L,
                        "test123",
                        "test123",
                        "ROLE_ADMIN"));
        Mockito.when(userRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user.get());
        assertThrowsExactly(RecordNotFoundException.class, () -> userService.updateUser(new User(1L,
                "test123",
                "test123",
                "ROLE_ADMIN")));
    }

    /**
     * @implNote testing for deleting user successfully
     */
    @Test
    public void deleteUserTest() {
        Optional<User> user = Optional
                .of(new User(1L,
                        "test123",
                        "test123",
                        "ROLE_ADMIN"));
        Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn(user);
        Mockito.doNothing().when(userRepo).deleteById(Mockito.anyLong());
        assertDoesNotThrow(() -> userService.deleteUser(1L));
    }

    /**
     * @implNote testing for deleting user with failed case
     */
    @Test
    public void deleteUserFailedTest() {
        Optional<User> user = Optional
                .of(new User(1L,
                        "test123",
                        "test123",
                        "ROLE_ADMIN"));
        Mockito.when(userRepo.findById(Mockito.anyLong())).thenThrow(RecordNotFoundException.class);
        Mockito.doNothing().when(userRepo).deleteById(Mockito.anyLong());
        assertThrowsExactly(RecordNotFoundException.class, () -> userService.deleteUser(1L));
    }
}
