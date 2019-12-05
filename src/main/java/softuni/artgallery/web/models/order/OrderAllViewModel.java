package softuni.artgallery.web.models.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAllViewModel {
    private String id;
    private LocalDateTime placedOn;
    private String username;
    private BigDecimal totalPrice;

    public OrderAllViewModel() {
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
}
