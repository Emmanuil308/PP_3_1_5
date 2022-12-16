package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.ServiceUser;


@Controller
@RequestMapping("/api")
public class ControllerUser {

    private ServiceUser serviceUser;

    @Autowired
    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping(value = "/admin")
    public String adminHomePage(Model model) {    // modelS

        return "admin_home_page";
    }

    @GetMapping(value = "/user")
    public String userHomePage(Model model) {

        return "user_home_page";
    }


}
