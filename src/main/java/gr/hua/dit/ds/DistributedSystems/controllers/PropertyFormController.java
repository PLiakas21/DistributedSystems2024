package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.service.PropertyFormService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/propertyForm")
public class PropertyFormController {

    private PropertyFormService propertyFormService;

    public PropertyFormController(PropertyFormService propertyFormService) {
        this.propertyFormService = propertyFormService;
    }

    @GetMapping
    public String redirectToList() {
        return "redirect:form/propertyForm/list";
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
            return "redirect:form/propertyForm/list";
        }
        model.addAttribute("propertyForm", propertyForm);
        return "form/propertyFormDetail";
    }

    // Create a new Property Form
    @GetMapping("/create")
    public String createPropertyForm(Model model) {
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        model.addAttribute("propertyForm", new PropertyForm());
        return "form/propertyForm";
    }

    // Save Property Form to the database
    @PostMapping("/save")
    public String savePropertyForm(@ModelAttribute PropertyForm propertyForm, Model model) {
        propertyFormService.savePropertyForm(propertyForm);
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "redirect:form/propertyForm/list";
    }

    // Delete a Property Form
    @GetMapping("/delete/{id}")
    public String deletePropertyForm(@PathVariable Integer id, Model model) {
        propertyFormService.deletePropertyForm(id);
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "redirect:form/propertyForm/list";
    }

}
