package gr.hua.dit.ds.DistributedSystems.controllers;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.RoleRepository;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Controller
public class AuthController {


    RoleRepository roleRepository;
    UserService userService;

    public AuthController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
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
    public String registerUser(@Valid User user, BindingResult bindingResult) {

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
        return "redirect:/login";
    }

    public void refreshAuthentication(User user) {

        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                user,
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    @PostMapping("/selectRole")
    public String selectRole(@RequestParam("role") String role, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            UserDetails userDetails = userService.loadUserByUsername(username);
            User currentUser = (User) userDetails;

            userService.addRole(currentUser.getId(), role);

            refreshAuthentication(currentUser);
        }
        return "redirect:/";
    }
}