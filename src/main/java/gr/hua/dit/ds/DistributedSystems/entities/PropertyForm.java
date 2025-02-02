package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PropertyForm extends Form {

    @Column(nullable = false)
    private String propertyAddress;

    @Column(nullable = false)
    private double rentPrice;

    @Column(nullable = false)
    private boolean openForRenting;

    @OneToMany(cascade= {CascadeType.ALL})
    private List<RentalForm> rentalFormList;

    public PropertyForm(Integer id, User user, String date, String address, String propertyAddress, double rentPrice, boolean openForRenting) {
        super(id, user, date, address);
        this.propertyAddress = propertyAddress;
        this.rentPrice = rentPrice;
        this.openForRenting = openForRenting;
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

    public boolean isOpenForRenting() {
        return openForRenting;
    }

    public void setOpenForRenting(boolean openForRenting) {
        this.openForRenting = openForRenting;
    }
}
