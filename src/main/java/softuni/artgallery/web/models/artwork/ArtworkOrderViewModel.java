package softuni.artgallery.web.models.artwork;

import softuni.artgallery.data.models.Category;

import java.math.BigDecimal;

public class ArtworkOrderViewModel {

    private String name;
    private BigDecimal price;
    private Category category;

    public ArtworkOrderViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
