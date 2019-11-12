package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {
    private String name;
    private String livesIn;
    private String history;
    private String education;
    private byte[] image;
    private String email;
    private List<Artwork> artworks;

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true, updatable = false)
    @Length(min = 5, max = 35)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Column(name = "lives_in", nullable = false)
    @Length(max = 35)
    public String getLivesIn() {
        return livesIn;
    }

    public void setLivesIn(String livesIn) {
        this.livesIn = livesIn;
    }

    @NotEmpty
    @Column(name = "info", nullable = false)
    @Length(max = 255)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Column(name = "education")
    @Length(max = 30)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @NotEmpty
    @Column(name = "image", nullable = false)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Email
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "artist", targetEntity = Artwork.class)
    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }
}
