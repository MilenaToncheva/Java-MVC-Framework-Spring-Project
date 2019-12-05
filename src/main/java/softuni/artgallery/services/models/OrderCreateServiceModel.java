package softuni.artgallery.services.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderCreateServiceModel {
    private LocalDateTime placedOn;
    private List<ArtworkServiceModel> artworks;
    private UserServiceModel userServiceModel;
    private BigDecimal totalPrice;

    public OrderCreateServiceModel() {
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

    public UserServiceModel getUserServiceModel() {
        return userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
