package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.PropertyFormRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class PropertyFormService {

    private final PropertyFormRepository propertyFormRepository;
    private final UserService userService;

    public PropertyFormService(PropertyFormRepository propertyFormRepository, UserService userService) {
        this.propertyFormRepository = propertyFormRepository;
        this.userService = userService;
    }

    @Transactional
    public LinkedHashSet<PropertyForm> getPropertyForms() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return propertyFormRepository.findAll().stream()
                .sorted(Comparator.comparing(form -> LocalDateTime.parse(form.getDate(), formatter), Comparator.reverseOrder()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Transactional
    public PropertyForm getPropertyForm(Integer id) throws EntityNotFoundException{
        return propertyFormRepository.findById(id).orElseThrow(() -> new RuntimeException("Property form not found"));
    }

    @Transactional
    public void savePropertyForm(User user, PropertyForm propertyForm) {
        propertyForm.setUser(user);
        propertyForm.setStatus(0);
        propertyForm.setOpenForRenting(true);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        propertyForm.setDate(formattedDate);
        propertyFormRepository.save(propertyForm);
        userService.addForm(user, propertyForm);
    }

    @Transactional
    public void changePropertyFormStatus(Integer id, Integer status) {
        PropertyForm propertyForm = propertyFormRepository.findById(id).orElseThrow(() -> new RuntimeException("Property form not found"));
        propertyForm.setStatus(status);
    }

    @Transactional
    public void changeRentStatus(Integer id, boolean status) {
        PropertyForm propertyForm = propertyFormRepository.findById(id).orElseThrow(() -> new RuntimeException("Property form not found"));
        propertyForm.setOpenForRenting(status);
    }
}
