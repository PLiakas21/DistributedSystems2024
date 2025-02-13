package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.RentalFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RentalFormService {

    private final RentalFormRepository rentalFormRepository;
    private final PropertyFormService propertyFormService;
    private final UserService userService;

    public RentalFormService(RentalFormRepository rentalFormRepository, PropertyFormService propertyFormService, UserService userService) {
        this.rentalFormRepository = rentalFormRepository;
        this.propertyFormService = propertyFormService;
        this.userService = userService;
    }

    @Transactional
    public List<RentalForm> getRentalForms() {
        return rentalFormRepository.findAll();
    }

    @Transactional
    public RentalForm getRentalForm(Integer id) {
        return rentalFormRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental form not found"));
    }

    @Transactional
    public void saveRentalForm(User user, Integer id) {
        PropertyForm propertyForm = propertyFormService.getPropertyForm(id);

        RentalForm rentalForm = new RentalForm();
        rentalForm.setUser(user);
        rentalForm.setAddress(propertyForm.getAddress());
        rentalForm.setStatus(false);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        rentalForm.setDate(formattedDate);

        propertyForm.getRentalFormList().add(rentalForm);

        rentalFormRepository.save(rentalForm);
        userService.addForm(user, rentalForm);
    }
}
