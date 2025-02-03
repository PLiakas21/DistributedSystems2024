package gr.hua.dit.ds.DistributedSystems.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@Table(name="app_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phone")
            })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Surname is required")
    private String surname;

    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Phone is required")
    @Size(min=10,max=10)
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must contain only digits")
    private String phone;

    @OneToMany(cascade= {CascadeType.ALL})
    private List<Form> formList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String name, String surname, String password, String email, String phone) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
