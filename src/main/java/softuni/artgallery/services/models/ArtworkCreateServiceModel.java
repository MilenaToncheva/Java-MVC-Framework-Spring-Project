package softuni.artgallery.services.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import softuni.artgallery.constants.artworksMessages.ArtworkRegisterViolationMessages;
import softuni.artgallery.data.models.Category;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class ArtworkCreateServiceModel {
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String description;
    private String artistName;
    private Category category;

    public ArtworkCreateServiceModel() {
    }

    @Length(min = 5, max = 30, message = ArtworkRegisterViolationMessages.ARTWORK_INCORRECT_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = ArtworkRegisterViolationMessages.ARTWORK_NOT_NULL_IMAGE_URL)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




    @NotEmpty(message = ArtworkRegisterViolationMessages.ARTWORK_INCORRECT_PRICE)
    @DecimalMin("0.01")
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

    @NotEmpty(message = ArtworkRegisterViolationMessages.ARTWORK_NOT_NULL_ARTIST_NAME)
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @NotEmpty(message = ArtworkRegisterViolationMessages.ARTWORK_NOT_NULL_CATEGORY)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
