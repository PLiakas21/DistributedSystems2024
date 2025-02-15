package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public LinkedHashSet<RentalForm> getRentalFormListSorted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return rentalFormList.stream()
                .sorted(Comparator.comparing(form -> LocalDateTime.parse(form.getDate(), formatter), Comparator.reverseOrder()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
