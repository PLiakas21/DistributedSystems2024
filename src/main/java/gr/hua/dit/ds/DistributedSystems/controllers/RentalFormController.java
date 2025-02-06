package gr.hua.dit.ds.DistributedSystems.controllers;

import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.service.RentalFormService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/rentalForm")
public class RentalFormController {

    private RentalFormService rentalFormService;

    public RentalFormController(RentalFormService rentalFormService) {
        this.rentalFormService = rentalFormService;
    }

    @GetMapping
    public String redirectToList() {
        return "redirect:form/rentalForm/list";
    }

    // List the Rental Forms
    @GetMapping("/list")
    public String listRentalForms(Model model) {
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "form/rentalForm";
    }

    @GetMapping("/{id}")
    public String showRentalFormDetails(@PathVariable("id") Integer id, Model model) {
        RentalForm rentalForm = rentalFormService.getRentalForm(id);
        model.addAttribute("rentalForm", rentalForm);
        return "form/rentalFormDetail";
    }


    // Create a new Rental Form
    @GetMapping("/create")
    public String createRentalForm(Model model) {
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        model.addAttribute("rentalForm", new RentalForm());
        return "form/rentalForm";
    }

    // Save Rental Form to the database
    @PostMapping("/save")
    public String saveRentalForm(@ModelAttribute RentalForm rentalForm, Model model) {
        rentalFormService.saveRentalForm(rentalForm);
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "redirect:form/rentalForm/list";
    }

    // Delete a Rental Form
    @GetMapping("/delete/{id}")
    public String deleteRentalForm(@PathVariable Integer id, Model model) {
        rentalFormService.deleteRentalForm(id);
        model.addAttribute("rentalForms", rentalFormService.getRentalForms());
        return "redirect:form/rentalForm/list";
    }

    @GetMapping("/accept/{id}")
    public String acceptRentalForm(@PathVariable("id") Integer id, Model model) {
        RentalForm rentalForm = rentalFormService.getRentalForm(id);
        if (rentalForm != null) {
            rentalForm.setStatus(true);
            rentalFormService.saveRentalForm(rentalForm);
        }
        return "redirect:/rentalForm/details/" + id;
    }
    @GetMapping("/reject/{id}")
    public String rejectRentalForm(@PathVariable("id") Integer id, Model model) {
        rentalFormService.deleteRentalForm(id);
        return "redirect:/rentalForm/list";
    }
}
