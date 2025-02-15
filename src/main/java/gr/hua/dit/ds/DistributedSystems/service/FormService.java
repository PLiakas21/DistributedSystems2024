package gr.hua.dit.ds.DistributedSystems.service;

import gr.hua.dit.ds.DistributedSystems.entities.Form;
import gr.hua.dit.ds.DistributedSystems.entities.RentalForm;
import gr.hua.dit.ds.DistributedSystems.repositories.FormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Transactional
    public void deleteForm(Integer id) {
        Form form = formRepository.findById(id).orElseThrow(() -> new RuntimeException("Form not found"));
        form.getUser().removeForm(form);
        if (form instanceof RentalForm)
            ((RentalForm) form).getPropertyForm().getRentalFormList().remove(form);
        formRepository.deleteById(id);
    }
}
