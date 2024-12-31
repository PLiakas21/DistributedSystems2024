package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.repositories.PropertyFormRepository;
import gr.hua.dit.ds.DistributedSystems.service.PropertyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Optional;

@Controller
@RequestMapping("/property-form")
public class PropertyFormController {

    private PropertyFormService propertyFormService;

    public PropertyFormController(PropertyFormService propertyFormService) {
        this.propertyFormService = propertyFormService;
    }

    // List the Property Forms
    @GetMapping("/list")
    public String listPropertyForms(Model model) {
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "property_form_list";
    }

    @GetMapping("/get/{id}")
    public String getPropertyForm(@ModelAttribute Integer id, Model model) {
        model.addAttribute("propertyForm", propertyFormService.getPropertyForm(id));
        return "property_form";
    }

    // Create a new Property Form
    @GetMapping("/create")
    public String createPropertyForm(Model model) {
        model.addAttribute("propertyForm", new PropertyForm());
        return "property_form_create";
    }

    // Save Property Form to the database
    @PostMapping("/save")
    public String savePropertyForm(@ModelAttribute PropertyForm propertyForm, Model model) {
        propertyFormService.savePropertyForm(propertyForm);
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "redirect:/property-form/list";
    }

    // Delete a Property Form
    @GetMapping("/delete/{id}")
    public String deletePropertyForm(@PathVariable Integer id, Model model) {
        propertyFormService.deletePropertyForm(id);
        model.addAttribute("propertyForms", propertyFormService.getPropertyForms());
        return "redirect:/property-form/list";
    }
}
