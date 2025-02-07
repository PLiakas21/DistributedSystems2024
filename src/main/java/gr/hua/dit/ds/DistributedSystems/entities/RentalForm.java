package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

@Entity
public class RentalForm extends Form {

    public RentalForm(User user, String date, String address) {
        super(user, date, address);
    }

    public RentalForm() {
        super();
    }
}