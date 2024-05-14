package com.maids.librarymanagementsystem.service;

import com.maids.librarymanagementsystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);

}
