package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.service.RentalFormService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/rentalForm")
public class RentalFormController {

    private final RentalFormService rentalFormService;

    public RentalFormController(RentalFormService rentalFormService) {
        this.rentalFormService = rentalFormService;
    }

    @GetMapping("/list")
    public String listRentalForms(Model model) {
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "";
    }

    @GetMapping("/{id}")
    public String showRentalFormDetails(@PathVariable("id") Integer id, Model model) {
        RentalForm rentalForm = rentalFormService.getRentalForm(id);
        model.addAttribute("rentalForm", rentalForm);
        return "form/rentalFormDetails";
    }


    // Create a new Rental Form
    @GetMapping("/create/{id}")
    public String applyForRental(@PathVariable Integer id, @AuthenticationPrincipal User user, Model model) {
        rentalFormService.saveRentalForm(user, id);
        return "forward:/propertyForm/" + id;
    }

    // Delete a Rental Form
    @GetMapping("/delete/{id}")
    public String deleteRentalForm(@PathVariable Integer id, Model model) {
        rentalFormService.deleteRentalForm(id);
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "";
    }

    @GetMapping("/accept/{id}")
    public String acceptRentalForm(@PathVariable("id") Integer id, Model model) {
        RentalForm rentalForm = rentalFormService.getRentalForm(id);
        return "" + id;
    }
    @GetMapping("/reject/{id}")
    public String rejectRentalForm(@PathVariable("id") Integer id, Model model) {
        rentalFormService.deleteRentalForm(id);
        return "";
    }
}
