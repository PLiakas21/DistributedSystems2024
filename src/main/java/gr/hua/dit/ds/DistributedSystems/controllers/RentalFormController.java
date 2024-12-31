package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.service.RentalFormService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rental-form")
public class RentalFormController {

    private RentalFormService rentalFormService;

    public RentalFormController(RentalFormService rentalFormService) {
        this.rentalFormService = rentalFormService;
    }

    // List the Rental Forms
    @GetMapping("/list")
    public String listRentalForms(Model model) {
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "rental_form_list";
    }

    @GetMapping("/get/{id}")
    public String getRentalForm(@ModelAttribute Integer id, Model model) {
        model.addAttribute("rentalForm", rentalFormService.getRentalForm(id));
        return "rental_form";
    }

    // Create a new Rental Form
    @GetMapping("/create")
    public String createRentalForm(Model model) {
        model.addAttribute("rentalForm", new RentalForm());
        return "rental_form_create";
    }

    // Save Rental Form to the database
    @PostMapping("/save")
    public String saveRentalForm(@ModelAttribute RentalForm rentalForm, Model model) {
        rentalFormService.saveRentalForm(rentalForm);
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "redirect:/rental-form/list";
    }

    // Delete a Rental Form
    @GetMapping("/delete/{id}")
    public String deleteRentalForm(@PathVariable Integer id, Model model) {
        rentalFormService.deleteRentalForm(id);
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "redirect:/rental-form/list";
    }
}
