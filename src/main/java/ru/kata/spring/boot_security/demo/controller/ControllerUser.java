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


    @PostMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("newUser") User user) {

        serviceUser.saveUser(user);

        return "redirect:/api/admin";
    }

    @RequestMapping(value = "admin/updateUser")
    public String updateUser(@ModelAttribute("upUser") User user) {

        serviceUser.saveUser(user);

        return "redirect:/api/admin";
    }

    @RequestMapping(value = "admin/deleteUser")
    public String deleteUser(@ModelAttribute("id") int id) {

        serviceUser.removeUserById(id);

        return "redirect:/api/admin";
    }

    @GetMapping(value = "/admin")
    public String adminHomePage(Model model) {
//        model.addAttribute("allUsers", serviceUser.getAllUser());
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User userAuth = serviceUser.getUserByEmail(auth.getName());
//        userAuth.setRolesUserAndAdmin();
//        model.addAttribute("oneUser", userAuth);
//
//        User tempUser = new User();
//        model.addAttribute("newUser", tempUser);
        return "admin_home_page";
    }

    @GetMapping(value = "/user")
    public String userHomePage(Model model) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User userAuth = serviceUser.getUserByEmail(auth.getName());
//        userAuth.setRolesUserAndAdmin();
//        model.addAttribute("oneUser", userAuth);

        return "user_home_page";
    }

}
