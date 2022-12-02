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


    @GetMapping("/admin/crud")
    public String showAllUser(Model model) {

        model.addAttribute("allUsers", serviceUser.getAllUser());

        return "show-all";
    }

    @GetMapping("/admin/addUser")
    public String addNewUser(Model model) {

        User tempUser = new User();
        model.addAttribute("saveOrUpdateUser", tempUser);

        return "user-field";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("saveOrUpdateUser") User user) {

        serviceUser.saveUser(user);

        return "redirect:/api/admin/crud";
    }

    @RequestMapping(value = "admin/updateUser")
    public String updateUser(@ModelAttribute("id") int id, Model model) {

        model.addAttribute("saveOrUpdateUser", serviceUser.getUserById(id));

        return "user-field";
    }

    @RequestMapping(value = "admin/deleteUser")
    public String deleteUser(@ModelAttribute("id") int id) {

        serviceUser.removeUserById(id);

        return "redirect:/api/admin/crud";
    }

    @GetMapping(value = "/admin")
    public String adminHomePage() {

        return "admin_home_page";
    }

    @GetMapping(value = "/user")
    public String userHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("oneUser", serviceUser.getUserByUserName(auth.getName()));

        return "user_home_page";
    }
}
