package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.repositories.RentalFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rental-form")
public class RentalFormController {

    @Autowired
    private RentalFormRepository rentalFormRepository;

    // List the Rental Forms
    @GetMapping("/list")
    public String listRentalForms(Model model) {
        model.addAttribute("rentalForms", rentalFormRepository.findAll());
        return "rental_form_list";
    }

    // Create a new Rental Form
    @GetMapping("/create")
    public String createRentalForm(Model model) {
        model.addAttribute("rentalForm", new RentalForm());
        return "rental_form_create";
    }

    // Save Rental Form to the database
    @PostMapping("/save")
    public String saveRentalForm(@ModelAttribute RentalForm rentalForm) {
        rentalFormRepository.save(rentalForm);
        return "redirect:/rental-form/list";
    }

    // Delete a Rental Form
    @GetMapping("/delete/{id}")
    public String deleteRentalForm(@PathVariable int id) {
        rentalFormRepository.deleteById(id);
        return "redirect:/rental-form/list";
    }

    // Update an existing Rental Form
    @GetMapping("/edit/{id}")
    public String editRentalForm(@PathVariable int id, Model model) {
        Optional<RentalForm> rentalForm = rentalFormRepository.findById(id);
        rentalForm.ifPresent(form -> model.addAttribute("rentalForm", form));
        return "rental_form_create";
    }
}
