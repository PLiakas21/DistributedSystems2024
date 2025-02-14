package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.PropertyFormService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/propertyForm")
public class PropertyFormController {

    private final PropertyFormService propertyFormService;

    public PropertyFormController(PropertyFormService propertyFormService) {
        this.propertyFormService = propertyFormService;
    }

    // List the Property Forms
    @GetMapping("/list")
    public String listPropertyForms(Model model) {
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "form/propertyPage";
    }

    @GetMapping("/{id}")
    public String showPropertyFormDetail(@PathVariable("id") Integer id, Model model) {
        PropertyForm propertyForm = propertyFormService.getPropertyForm(id);
        model.addAttribute("propertyForm", propertyForm);
        return "form/propertyFormDetails";
    }

    @GetMapping("/create")
    public String createPropertyForm(Model model) {
        model.addAttribute("propertyForm", new PropertyForm());
        return "form/propertyForm";
    }

    @PostMapping("/save")
    public String savePropertyForm(@AuthenticationPrincipal User user, @ModelAttribute PropertyForm propertyForm) {
        propertyFormService.savePropertyForm(user, propertyForm);
        return "redirect:/user/myForms";
    }

    @GetMapping("/changePropertyFormStatus/{id}/{status}")
    public String changePropertyFormStatus(@PathVariable("id") Integer id, Model model, @PathVariable("status") Integer status) {
        propertyFormService.changePropertyFormStatus(id, status);
        model.addAttribute("msg", "Property approved");
        return "forward:/user/viewForms/" + propertyFormService.getPropertyForm(id).getUser().getId();
    }

    @GetMapping("/switchRentalStatus/{id}/{status}")
    public String switchRentalStatus(@PathVariable Integer id, @PathVariable Boolean status ,Model model) {
        propertyFormService.changeRentStatus(id, status);
        model.addAttribute("msg", "Rental Status changed");
        return "forward:/propertyForm/" + id;
    }

    @GetMapping("/listFormApplications/{id}")
    public String listFormApplications(@PathVariable Integer id, Model model) {
        PropertyForm propertyForm = propertyFormService.getPropertyForm(id);
        model.addAttribute("rentalFormList", propertyForm.getRentalFormListSorted());
        return "form/rentalFormList";
    }
}
