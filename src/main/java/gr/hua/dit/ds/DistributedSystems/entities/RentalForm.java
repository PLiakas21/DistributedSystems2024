package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

@Entity
public class RentalForm extends Form {

    @ManyToOne
    private PropertyForm propertyForm;

    public RentalForm(User user, String date, String address) {
        super(user, date, address);
    }

    public RentalForm() {
        super();
    }

    public PropertyForm getPropertyForm() {
        return propertyForm;
    }

    public void setPropertyForm(PropertyForm propertyForm) {
        this.propertyForm = propertyForm;
    }
}