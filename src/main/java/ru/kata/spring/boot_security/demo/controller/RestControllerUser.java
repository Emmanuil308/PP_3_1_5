package ru.kata.spring.boot_security.demo.controller;

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
    public List<User> getAllUsers() {
        return serviceUser.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable ("id") int id) {
        return serviceUser.getUserById(id);
    }

    @PostMapping("/users")
    public User saveNewUser(@RequestBody User user) {

        serviceUser.saveUser(user);

        return user;
    }

    @PutMapping(value = "/users")
    public User updateUser(@RequestBody User user) {

        serviceUser.saveUser(user);

        return user;
    }

    @DeleteMapping("/users/{id}")
    public String removeUserById(@PathVariable ("id") int id) {
        serviceUser.removeUserById(id);

        return "User with id=" + id + " delete from database";
    }

    @GetMapping(value = "/users/name")
    public User getUserByName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return serviceUser.getUserByEmail(auth.getName());
    }
}
