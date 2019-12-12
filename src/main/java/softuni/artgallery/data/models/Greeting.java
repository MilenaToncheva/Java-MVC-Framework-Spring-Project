package softuni.artgallery.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="greetings")
public class Greeting extends BaseEntity {
    private String name;
    private int duration;
    private String message1;
    private String message2;

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
@Column
    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }
@Column
    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
