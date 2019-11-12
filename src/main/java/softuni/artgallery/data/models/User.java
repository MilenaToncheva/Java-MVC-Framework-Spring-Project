package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
    private Set<Role> authorities;

    public User() {
    }

    @NotEmpty
    @Column(name = "username", nullable = false, unique = true, updatable = false)
    @Length(min = 5, max = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    @Column(name = "first_name", nullable = false, updatable = false)
    @Length(min = 5, max = 15)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    @Column(name = "last_name", nullable = false, updatable = false)
    @Length(min = 5, max = 15)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @NotEmpty
    @Column(name = "email", nullable = false, updatable = false, unique = true)
    @Length(max = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty
    @Column(name = "password", nullable = false)
    @Length(min = 5, max = 30)
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

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
