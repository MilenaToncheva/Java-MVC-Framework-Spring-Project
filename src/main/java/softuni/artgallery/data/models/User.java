package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;
import softuni.artgallery.constants.userMessages.UserRegistrationViolationMessages;
import softuni.artgallery.web.validations.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Artwork> favouriteArtworks;
    private List<Order> orders;


    public User() {


    }


    @Column(name = "username", nullable = false, unique = true, updatable = false)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Column(name = "first_name", nullable = false, updatable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "last_name", nullable = false, updatable = false)

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "email", nullable = false, updatable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "users_artworks",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artwork_id", referencedColumnName = "id"))
    public Set<Artwork> getFavouriteArtworks() {
        return favouriteArtworks;
    }

    public void setFavouriteArtworks(Set<Artwork> favouriteArtworks) {
        this.favouriteArtworks = favouriteArtworks;
    }

    @OneToMany(mappedBy = "user", targetEntity = Order.class)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
