package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.entities.User;
import gr.hua.dit.ds.DistributedSystems.repositories.PropertyFormRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        propertyFormRepository.save(propertyForm);
    }

    @Transactional
    public void deletePropertyForm(Integer id) {
        propertyFormRepository.deleteById(id);
    }
}
