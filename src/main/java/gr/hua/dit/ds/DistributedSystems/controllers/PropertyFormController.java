package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.PropertyFormService;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/propertyForm")
public class PropertyFormController {

    private final PropertyFormService propertyFormService;
    private final UserService userService;

    public PropertyFormController(PropertyFormService propertyFormService, UserService userService) {
        this.propertyFormService = propertyFormService;
        this.userService = userService;
    }

    // List the Property Forms
    @GetMapping("/list")
    public String listPropertyForms(Model model) {
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "form/propertyForm";
    }

    @GetMapping("/{id}")
    public String showPropertyFormDetail(@PathVariable("id") Integer id, Model model) {
        PropertyForm propertyForm = propertyFormService.getPropertyForm(id);
        if (propertyForm == null) {
            return "form/propertyForm";
        }
        model.addAttribute("propertyForm", propertyForm);
        return "form/propertyFormDetail";
    }

    @GetMapping("/create")
    public String createPropertyForm(Model model) {
        model.addAttribute("propertyForm", new PropertyForm());
        return "form/propertyForm";
    }

    @PostMapping("/save")
    public String savePropertyForm(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute PropertyForm propertyForm) {
        propertyFormService.savePropertyForm((User) userService.loadUserByUsername(userDetails.getUsername()), propertyForm);
        return "form/propertyForm";
    }

    @GetMapping("/delete/{id}")
    public String deletePropertyForm(@PathVariable Integer id) {
        propertyFormService.deletePropertyForm(id);
        return "form/propertyForm";
    }
}
