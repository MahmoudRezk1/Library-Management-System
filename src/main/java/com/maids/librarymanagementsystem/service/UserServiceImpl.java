package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.User;
import com.maids.librarymanagementsystem.excpetions.RecordNotFoundException;
import com.maids.librarymanagementsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote service class for user api
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final PasswordEncoder passwordEncoder;

    /**
     * @implNote  method to retrieve all stored users
     * @return List of users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    /**
     * @implNote  method to retrieve user with specific username
     * @param username represent the username of user
     * @return user object of the target user
     */
    @Override
    public User findUserByUsername(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else throw new RecordNotFoundException("Username or password is not correct");

    }

    /**
     * @implNote  method to insert user to our system
     * @param user represent the object of target user to store it
     * @return user object of the saved user
     */
    @Override
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    /**
     * @implNote  method to update user in our system
     * @param user represent the object of target user to update it
     * @return user object of the updated user
     */
    @Override
    public User updateUser(User user) {
        Optional<User> oldUser = userRepo.findById(user.getId());
        if (oldUser.isPresent()) {
            oldUser.get().setUsername(user.getUsername());
            oldUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
            oldUser.get().setRole(user.getRole());

            return userRepo.save(oldUser.get());
        } else
            throw new RecordNotFoundException("User with id: " + user.getId() + " not found");
    }

    /**
     * @implNote  method to delete a user from our system
     * @param id represent the id of target user to delete it
     */
    @Override
    public void deleteUser(Long id) {
        Optional<User> oldUser = userRepo.findById(id);
        if (oldUser.isPresent()) {
            userRepo.deleteById(id);
        } else
            throw new RecordNotFoundException("User with id: " + id + " not found");
    }
}
