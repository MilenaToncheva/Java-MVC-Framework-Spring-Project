package softuni.artgallery.data.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private LocalDateTime placedOn;
    private List<Artwork> artworks;
    private User user;
    private BigDecimal totalPrice;

    public Order() {
        this.artworks = new ArrayList<>();
    }

    @Column(name = "placed_on", nullable = false)
    public LocalDateTime getPlacedOn() {
        return placedOn;
    }

    public void setPlacedOn(LocalDateTime placedOn) {
        this.placedOn = placedOn;
    }

    @OneToMany(mappedBy = "order", targetEntity = Artwork.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}

