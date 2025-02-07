package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.PropertyFormRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PropertyFormService {

    private final PropertyFormRepository propertyFormRepository;

    public PropertyFormService(PropertyFormRepository propertyFormRepository) {
        this.propertyFormRepository = propertyFormRepository;
    }

    @Transactional
    public List<PropertyForm> getPropertyForms() {
        return propertyFormRepository.findAll();
    }

    @Transactional
    public PropertyForm getPropertyForm(Integer id) throws EntityNotFoundException{
        return propertyFormRepository.findById(id).orElseThrow(() -> new RuntimeException("Property form not found"));
    }

    @Transactional
    public void savePropertyForm(User user, PropertyForm propertyForm) {
        propertyForm.setUser(user);
        propertyForm.setOpenForRenting(true);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        propertyForm.setDate(formattedDate);
        propertyFormRepository.save(propertyForm);
    }

    @Transactional
    public void deletePropertyForm(Integer id) {
        propertyFormRepository.deleteById(id);
    }
}
