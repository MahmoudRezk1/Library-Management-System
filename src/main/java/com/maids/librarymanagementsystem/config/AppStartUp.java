package com.maids.librarymanagementsystem.config;

import com.maids.librarymanagementsystem.entity.User;
import com.maids.librarymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class for initialize two users on running application process
 */
@Configuration
@RequiredArgsConstructor
public class AppStartUp implements CommandLineRunner {
    private final UserService userService;
    @Override
    public void run(String... args) throws Exception {
        userService.createUser(new User(null,"admin","admin","ROLE_ADMIN"));
        userService.createUser(new User(null,"librarian","librarian","ROLE_LIBRARIAN"));
    }
}
