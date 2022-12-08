package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.DaoUser;
import ru.kata.spring.boot_security.demo.entity.User;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private DaoUser daoUser;

    @Autowired
    public UserDetailServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        try {
            user = daoUser.getUserByEmail(email);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found!!!");
        }
        return SecurityUser.fromMyUser(user);

    }
}
