package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.PropertyForm;
import gr.hua.dit.ds.DistributedSystems.repositories.PropertyFormRepository;
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
    public PropertyForm getPropertyForm(Integer id) {
        return propertyFormRepository.findById(id).get();
    }

    @Transactional
    public void savePropertyForm(PropertyForm propertyForm) {
        propertyFormRepository.save(propertyForm);
    }

    @Transactional
    public void deletePropertyForm(Integer id) {
        propertyFormRepository.deleteById(id);
    }
}
