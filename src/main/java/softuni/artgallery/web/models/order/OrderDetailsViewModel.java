package softuni.artgallery.web.models.order;

import softuni.artgallery.web.models.artwork.ArtworkOrderViewModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsViewModel {
    private String id;
    private LocalDateTime placedOn;
    private String username;
    private String firstName;
    private String lastName;
    private BigDecimal totalPrice;
    private List<ArtworkOrderViewModel> artworks;

    public OrderDetailsViewModel() {
        this.artworks=new ArrayList<>();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ArtworkOrderViewModel> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkOrderViewModel> artworks) {
        this.artworks = artworks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
