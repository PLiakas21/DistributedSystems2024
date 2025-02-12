package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class PropertyForm extends Form {

    @Column(nullable = false)
    private double rentPrice;

    @Column(nullable = false)
    private boolean openForRenting;

    @OneToMany(cascade= {CascadeType.ALL})
    private Set<RentalForm> rentalFormList;

    public PropertyForm(User user, String date, String address, double rentPrice, boolean openForRenting) {
        super(user, date, address);
        this.rentPrice = rentPrice;
        this.openForRenting = openForRenting;
        this.rentalFormList = new HashSet<>();
    }

    public PropertyForm() {
        super();
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

    public Set<RentalForm> getRentalFormList() {
        return rentalFormList;
    }

    public void setRentalFormList(Set<RentalForm> rentalFormList) {
        this.rentalFormList = rentalFormList;
    }

    public boolean hasUserApplication(String username){
        for (RentalForm rentalForm : rentalFormList) {
            if(rentalForm.getUser().getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
