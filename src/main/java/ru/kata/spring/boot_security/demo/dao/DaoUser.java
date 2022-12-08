package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;
import java.util.List;

public interface DaoUser {

    public List<User> getAllUser();

    public User getUserById(int id);

    public void saveUser(User user);

    public void removeUserById(int id);

    User getUserByEmail(String email);
}
