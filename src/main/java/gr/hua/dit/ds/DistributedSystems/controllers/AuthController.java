package gr.hua.dit.ds.DistributedSystems.controllers;
import gr.hua.dit.ds.DistributedSystems.entities.Role;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.RoleRepository;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {


    RoleRepository roleRepository;
    UserService userService;

    public AuthController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void setup() {
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_user = new Role("ROLE_USER");
        Role role_landlord = new Role("ROLE_LANDLORD");
        Role role_tenant = new Role("ROLE_TENANT");

        roleRepository.updateOrInsert(role_admin);
        roleRepository.updateOrInsert(role_user);
        roleRepository.updateOrInsert(role_landlord);
        roleRepository.updateOrInsert(role_tenant);
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "Username is already taken");
            return "auth/register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user", "Email is already registered");
            return "auth/register";
        }

        userService.saveUser(user);
        return "home";
    }
}