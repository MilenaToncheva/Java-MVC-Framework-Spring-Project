package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String name;
    private List<Artwork> artworks;

    public Category() {
    }

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    @Length(min = 5, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", targetEntity = Artwork.class)
    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }
}
