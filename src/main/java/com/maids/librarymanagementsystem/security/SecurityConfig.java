package com.maids.librarymanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author mahmoudrezk514@gmail.com
 * @implNote class to handle authentication and authorization using basic authentication
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * @implNote bean to handle encoding the password using Bcrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @implNote bean to handle authorization
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> {
                    auth.requestMatchers(HttpMethod.GET,"/api/books").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/api/books/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/api/books").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/books").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/books/**").hasAnyRole("ADMIN","LIBRARIAN");

                    auth.requestMatchers(HttpMethod.GET,"/api/patrons").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.GET,"/api/patrons/**").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.POST,"/api/patrons").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/patrons").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/patrons").hasAnyRole("ADMIN","LIBRARIAN");

                    auth.requestMatchers(HttpMethod.GET,"/api/borrow").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.POST,"/api/borrow").hasAnyRole("ADMIN","LIBRARIAN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/borrow").hasAnyRole("ADMIN","LIBRARIAN");

                    auth.requestMatchers(HttpMethod.GET,"/api/librarians").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/api/librarians/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/api/librarians").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/librarians").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/librarians/**").hasRole("ADMIN");

                    auth.requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.GET,"/api/users/**").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/api/users").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/api/users").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/api/users").hasRole("ADMIN");

                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
