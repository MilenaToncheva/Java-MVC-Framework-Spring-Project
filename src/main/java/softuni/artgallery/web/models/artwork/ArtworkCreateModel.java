package softuni.artgallery.web.models.artwork;

import org.springframework.web.multipart.MultipartFile;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.models.Category;

import java.math.BigDecimal;

public class ArtworkCreateModel {
    private String name;
    private MultipartFile image;
    private BigDecimal price;
    private String description;
    private String artistName;
    private Category category;

    public ArtworkCreateModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
