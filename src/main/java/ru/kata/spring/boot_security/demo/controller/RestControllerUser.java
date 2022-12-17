package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllerUser {

    private ServiceUser serviceUser;

    public RestControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

        try {
            List<User> allUsers = serviceUser.getAllUser();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        try {
            User user = serviceUser.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        try {
            serviceUser.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            serviceUser.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> removeUserById(@PathVariable("id") int id) {
        try {
            serviceUser.removeUserById(id);
            String response = "User with id=" + id + " delete from database";
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/name")
    public ResponseEntity<User> getUserByName() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User userAuth = serviceUser.getUserByEmail(auth.getName());

            return new ResponseEntity<>(userAuth,HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
