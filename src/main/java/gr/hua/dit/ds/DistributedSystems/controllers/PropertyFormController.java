package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.PropertyFormService;
import gr.hua.dit.ds.DistributedSystems.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String savePropertyForm(@AuthenticationPrincipal User user, @ModelAttribute PropertyForm propertyForm) {
        propertyFormService.savePropertyForm(user, propertyForm);
        return "form/propertyForm";
    }

    @GetMapping("/delete/{id}")
    public String deletePropertyForm(@PathVariable Integer id, Model model) {
        Integer userId = propertyFormService.getPropertyForm(id).getUser().getId();
        propertyFormService.deletePropertyForm(id);
        model.addAttribute("msg", "Form deleted");
        return "forward:/user/viewForms/" + userId;
    }

    @GetMapping("approveProperty/{id}")
    public String approvePropertyForm(@PathVariable("id") Integer id, Model model) {
        propertyFormService.approvePropertyForm(id);
        model.addAttribute("msg", "Property approved");
        return "forward:/user/viewForms/" + propertyFormService.getPropertyForm(id).getUser().getId();
    }
}
