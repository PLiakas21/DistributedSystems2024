package gr.hua.dit.ds.DistributedSystems.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            UserDetails userDetails = userService.loadUserByUsername(username);
            User currentUser= (User) userDetails;
            boolean showRoleSelection = currentUser.getRoles().stream()
                    .noneMatch(role -> role.getName().equals("ROLE_LANDLORD") || role.getName().equals("ROLE_TENANT") || role.getName().equals("ROLE_ADMIN"));
            model.addAttribute("showRoleSelection", showRoleSelection);
            model.addAttribute("currentUserId", currentUser.getId());
        }
        return "home";

    }
    @PostMapping("/selectRole")
    public String selectRole(@RequestParam("role") String role,Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            UserDetails userDetails = userService.loadUserByUsername(username);
            User currentUser= (User) userDetails;
           return "redirect:/user/addRole/" + currentUser.getId() + '/' + role;
        }
        return "redirect:/";
    }
}
