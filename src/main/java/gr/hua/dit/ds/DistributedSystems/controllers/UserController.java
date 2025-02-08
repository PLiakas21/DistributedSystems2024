package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.Role;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

//    @PostConstruct
//    public void setup(){
//        User user = userService.loadUserByUsername("admin");
//        userService.addRole(user.getId(), "ROLE_ADMIN");
//    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addRole/{id}/{role}")
    public String addRole(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("users", userService.getUsers());
        userService.addRole(id, role);
        String message = "Role added successfully";
        model.addAttribute("msg", message);
        return "user/user";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "user/user";
    }

    @GetMapping("/get/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getUsers());
        return "redirect:user/user/list";
    }

    @GetMapping("/viewForms/{id}")
    public String viewForms(@PathVariable Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("formList", user.getFormList());
        model.addAttribute("user", user);
        return "user/userForms";
    }
}
