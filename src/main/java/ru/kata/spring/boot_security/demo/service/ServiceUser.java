package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface ServiceUser {

    public List<User> getAllUser();

    public User getUserById(int id);

    public void saveUser(User user);

    public void removeUserById(int id);

    public User getUserByUserName(String userName);

}
