package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

@Entity
public class RentalForm extends Form {

    public RentalForm(Integer id, User user, String date, String address, double rentPrice) {
        super(id, user, date, address);
    }

    public RentalForm() {
        super();
    }
}
