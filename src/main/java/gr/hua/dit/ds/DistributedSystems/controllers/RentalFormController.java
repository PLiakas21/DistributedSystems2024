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

    @GetMapping("/create/{id}")
    public String applyForRental(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        rentalFormService.saveRentalForm(user, id);
        return "forward:/propertyForm/" + id;
    }

    @GetMapping("/changeRentalFormStatus/{id}/{status}")
    public String changeRentalFormStatus(@PathVariable("id") Integer id, Model model, @PathVariable("status") Integer status) {
        RentalForm rentalForm = rentalFormService.getRentalForm(id);
        rentalFormService.changeRentalFormStatus(id, status);
        model.addAttribute("msg", "Rental status changed");
        return "forward:/propertyForm/listFormApplications/" + rentalForm.getPropertyForm().getId();
    }
}
