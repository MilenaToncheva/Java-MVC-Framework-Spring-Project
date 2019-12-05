package softuni.artgallery.services.models;

import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.models.Category;
import softuni.artgallery.data.models.Order;

import java.math.BigDecimal;

public class ArtworkServiceModel {
    private String id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String description;
    private ArtistServiceModel artist;
    private OrderServiceModel order;
    private Category category;
    private boolean isAvailable;

    public ArtworkServiceModel() {
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArtistServiceModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistServiceModel artist) {
        this.artist = artist;
    }

    public OrderServiceModel getOrder() {
        return order;
    }

    public void setOrder(OrderServiceModel order) {
        this.order = order;
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
