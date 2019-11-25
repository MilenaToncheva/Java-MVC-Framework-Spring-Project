package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "artworks")
public class Artwork extends BaseEntity {
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String description;
    private Artist artist;
    private Order order;
    private Category category;
    private List<User> followers;

    public Artwork() {
    }

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Length(min = 2, max = 30)
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


    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany
    @JoinTable(name = "favourites_followers",
            joinColumns = @JoinColumn(name = "favourite_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="follower_id",referencedColumnName="id")
    )
    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
