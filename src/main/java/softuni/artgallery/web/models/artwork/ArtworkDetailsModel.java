package softuni.artgallery.web.models.artwork;

import softuni.artgallery.data.models.Category;
import softuni.artgallery.web.models.artist.ArtistViewModel;

import java.math.BigDecimal;

public class ArtworkDetailsModel {
    private String id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String description;
    private ArtistViewModel artist;
    private String artistId;
    private Category category;
    private boolean isAvailable;

    public ArtworkDetailsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ArtistViewModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistViewModel artist) {
        this.artist = artist;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
