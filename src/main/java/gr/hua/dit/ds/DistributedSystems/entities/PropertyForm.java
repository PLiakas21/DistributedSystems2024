package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

@Entity
public class PropertyForm extends Form {

    @Column(nullable = false)
    private String propertyAddress;

    @Column(nullable = false)
    private double rentPrice;

    public PropertyForm(Integer id, User user, String date, String address, String propertyAddress, double rentPrice) {
        super(id, user, date, address);
        this.propertyAddress = propertyAddress;
        this.rentPrice = rentPrice;
    }

    public PropertyForm() {
        super();
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }
}
