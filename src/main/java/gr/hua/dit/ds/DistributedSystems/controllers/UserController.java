package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // List the Users
    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "user_list";
    }

    @GetMapping("/get/{id}")
    public String getUser(@ModelAttribute Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    // Create a new User
    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user_create";
    }

    // Save User to the database
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("users", userService.getUsers());
        return "redirect:/user/list";
    }

    // Delete a User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getUsers());
        return "redirect:/user/list";
    }
}
