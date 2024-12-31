package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.repositories.RentalFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalFormService {

    private RentalFormRepository rentalFormRepository;

    public RentalFormService(RentalFormRepository rentalFormRepository) {
        this.rentalFormRepository = rentalFormRepository;
    }

    @Transactional
    public List<RentalForm> getRentalForms() {
        return rentalFormRepository.findAll();
    }

    @Transactional
    public RentalForm getRentalForm(Integer id) {
        return  rentalFormRepository.findById(id).get();
    }

    @Transactional
    public void saveRentalForm(RentalForm rentalForm) {
        rentalFormRepository.save(rentalForm);
    }

    @Transactional
    public void deleteRentalForm(Integer id) {
        rentalFormRepository.deleteById(id);
    }
}
