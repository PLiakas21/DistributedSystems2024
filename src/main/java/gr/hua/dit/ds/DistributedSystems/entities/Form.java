package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;

@Entity
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean status;

    public Form(int id, User user, String date, String address) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.address = address;
        this.status = false;
    }

    public Form() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
