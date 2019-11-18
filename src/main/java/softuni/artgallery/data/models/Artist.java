package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;
import softuni.artgallery.constants.artistMessages.ArtistRegisterViolationMessages;
import softuni.artgallery.constants.commonMessages.CommonConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {
    private String name;
    private String livesIn;
    private String history;
    private String education;
    private String imageUrl;
    private String email;
    private List<Artwork> artworks;


    @Column(name = "name", nullable = false, unique = true, updatable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "lives_in", nullable = false)
    public String getLivesIn() {
        return livesIn;
    }

    public void setLivesIn(String livesIn) {
        this.livesIn = livesIn;
    }


    @Column(name = "history", nullable = false)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


    @Column(name = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



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
