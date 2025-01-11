package gr.hua.dit.ds.DistributedSystems.controllers;
import gr.hua.dit.ds.DistributedSystems.entities.Role;
import gr.hua.dit.ds.DistributedSystems.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    RoleRepository roleRepository;

    public AuthController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void setup() {
        Role role_user = new Role("ROLE_USER");
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_landlord = new Role("ROLE_LANDLORD");
        Role role_tenant = new Role("ROLE_TENANT");

        roleRepository.updateOrInsert(role_user);
        roleRepository.updateOrInsert(role_admin);
        roleRepository.updateOrInsert(role_landlord);
        roleRepository.updateOrInsert(role_tenant);
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}