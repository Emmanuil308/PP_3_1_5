package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.DaoUser;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.exception.MyNotFoundException;
import ru.kata.spring.boot_security.demo.exception.MyNotRoleException;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserImpl implements ServiceUser {

    private DaoUser daoUser;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public ServiceUserImpl(DaoUser daoUser, PasswordEncoder passwordEncoder) {
        this.daoUser = daoUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<User> getAllUser() {
        return daoUser.getAllUser();
    }

    @Transactional
    public User getUserById(int id) {
        Optional<User> userOptional = Optional.ofNullable(daoUser.getUserById(id));

        if(userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new MyNotFoundException("This User does not exist");
        }
    }

    @Transactional
    public void saveUser(User user, String roles) {
        if(user.getRoleSet() == null & roles == null) {                 // Посмотреть как заработает с roles
            throw new MyNotRoleException("Role not selected for User");
        } else if (roles != null & user.getRoleSet() != null) {
            user.setRoleSet(null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        for (String str : roles.split(",")) {
            if (str.equals("ROLE_USER")) {
                user.addRoleForUser(new Role("ROLE_USER"));
            }
            if (str.equals("ROLE_ADMIN")) {
                user.addRoleForUser(new Role("ROLE_ADMIN"));
            }
        }
        daoUser.saveUser(user);
    }

    @Transactional
    public void removeUserById(int id) {
        daoUser.removeUserById(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return daoUser.getUserByEmail(email);

    }
}
