package com.maids.librarymanagementsystem.controller;

import com.maids.librarymanagementsystem.entity.User;
import com.maids.librarymanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author mahmoudrezk514@gmail.com
 * @implNote controller class for user api
 */
@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * @apiNote api to retrieve all stored users
     * @return responseEntity with list of users
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * @apiNote api to retrieve user with specific username
     * @param username represent the username of user
     * @return responseEntity of the target user
     */
    @GetMapping(path = "/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * @apiNote api to insert user to our system
     * @param user represent the object of target user to store it
     * @return responseEntity of the saved user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    /**
     * @apiNote api to update user in our system
     * @param user represent the object of target user to update it
     * @return responseEntity of the updated book
     */
    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    /**
     * @apiNote api to delete a user from our system
     * @param id represent the id of target user to delete it
     */
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
