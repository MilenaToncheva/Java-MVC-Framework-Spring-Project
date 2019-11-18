package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Table(name = "artworks")
public class Artwork extends BaseEntity {
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Artist artist;
    private Category category;

    public Artwork() {
    }

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Length(min = 5, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Column(name = "imageUrl", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotEmpty
    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
