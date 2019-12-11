package softuni.artgallery.services.models;

public class GreetingCreateServiceModel {
    private String name;
    private int duration;

    public GreetingCreateServiceModel() {
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
