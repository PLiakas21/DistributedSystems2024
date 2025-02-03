package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    @PostConstruct
    public void setup(){
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setName("ad");
        admin.setSurname("min");
        admin.setEmail("admin@gmail.com");
        admin.setPhone("9203492039");
        userService.setAdmin(admin);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String redirectToList() {
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "user/user";
    }

    @GetMapping("/get/{id}")
    public String getUser(@ModelAttribute Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user/user";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "user/user";
    }

    // Delete a User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getUsers());
        return "redirect:user/user/list";
    }
}
