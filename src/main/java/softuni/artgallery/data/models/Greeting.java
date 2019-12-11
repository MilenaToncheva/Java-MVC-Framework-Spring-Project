package softuni.artgallery.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="greetings")
public class Greeting extends BaseEntity {
    private String name;
    private int duration;

    public Greeting() {
    }
@Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
@Column
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
