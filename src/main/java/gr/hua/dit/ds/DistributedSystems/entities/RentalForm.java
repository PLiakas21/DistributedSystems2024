package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

@Entity
public class RentalForm extends Form {

    @Column(nullable = false)
    private double rentPrice;

    public RentalForm(Integer id, User user, String date, String address, double rentPrice) {
        super(id, user, date, address);
        this.rentPrice = rentPrice;
    }

    public RentalForm() {
        super();
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }
}
