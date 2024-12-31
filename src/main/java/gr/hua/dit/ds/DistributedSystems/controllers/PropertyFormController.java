package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.repositories.PropertyFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/property-form")
public class PropertyFormController {

    @Autowired
    private PropertyFormRepository propertyFormRepository;

    // List the Property Forms
    @GetMapping("/list")
    public String listPropertyForms(Model model) {
        model.addAttribute("propertyForms", propertyFormRepository.findAll());
        return "property_form_list";
    }

    // Create a new Property Form
    @GetMapping("/create")
    public String createPropertyForm(Model model) {
        model.addAttribute("propertyForm", new PropertyForm());
        return "property_form_create";
    }

    // Save Property Form to the database
    @PostMapping("/save")
    public String savePropertyForm(@ModelAttribute PropertyForm propertyForm) {
        propertyFormRepository.save(propertyForm);
        return "redirect:/property-form/list";
    }

    // Delete a Property Form
    @GetMapping("/delete/{id}")
    public String deletePropertyForm(@PathVariable int id) {
        propertyFormRepository.deleteById(id);
        return "redirect:/property-form/list";
    }

    // Update an existing Property Form
    @GetMapping("/edit/{id}")
    public String editPropertyForm(@PathVariable int id, Model model) {
        Optional<PropertyForm> propertyForm = propertyFormRepository.findById(id);
        propertyForm.ifPresent(form -> model.addAttribute("propertyForm", form));
        return "property_form_create";
    }
}
