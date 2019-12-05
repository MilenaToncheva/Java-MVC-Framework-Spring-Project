package softuni.artgallery.data.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private LocalDateTime startsOn;
    private LocalDateTime endsOn;


    public Event() {
        super();
    }

    @NotEmpty
    @Column(name = "name", nullable = false)
    @Length(min = 5, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotEmpty
    @Column(name = "location", nullable = false)
    @Length(min = 5, max = 15)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @NotEmpty
    @Column(name = "description", nullable = false)
    @Length(min = 5, max = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty
    @Column(name = "imageUrl", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    @Column(name = "starts_on", nullable = false)
    public LocalDateTime getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(LocalDateTime startsOn) {
        this.startsOn = startsOn;
    }


    @Column(name = "ends_on", nullable = false)
    public LocalDateTime getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(LocalDateTime endsOn) {
        this.endsOn = endsOn;
    }

}
