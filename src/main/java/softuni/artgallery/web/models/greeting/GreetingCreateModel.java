package softuni.artgallery.web.models.greeting;

public class GreetingCreateModel {
    private String name;
    private int duration;

    public GreetingCreateModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
