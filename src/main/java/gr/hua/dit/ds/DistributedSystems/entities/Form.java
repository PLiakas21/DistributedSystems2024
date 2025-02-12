package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    protected Integer id;

    @ManyToOne
    @JoinColumn(name="app_user_id", nullable = false)
    protected User user;

    @Column(nullable = false)
    protected String date;

    @Column(nullable = false)
    @NotBlank
    protected String address;

    @Column(nullable = false)
    protected boolean status;

    public Form(User user, String date, String address) {
        this.user = user;
        this.date = date;
        this.address = address;
        this.status = false;
    }

    public Form() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isApproved() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
