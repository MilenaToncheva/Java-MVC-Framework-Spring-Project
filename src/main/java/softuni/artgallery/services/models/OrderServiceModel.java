package softuni.artgallery.services.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceModel {
    private String id;
    private LocalDateTime placedOn;
    private List<ArtworkServiceModel> artworks;
    private UserServiceModel user;
    private BigDecimal totalPrice;

    public OrderServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getPlacedOn() {
        return placedOn;
    }

    public void setPlacedOn(LocalDateTime placedOn) {
        this.placedOn = placedOn;
    }

    public List<ArtworkServiceModel> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkServiceModel> artworks) {
        this.artworks = artworks;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
