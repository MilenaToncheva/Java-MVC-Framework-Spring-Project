package softuni.artgallery.ScheduledJob;

public class ActiveGreeting {
    public static String name="";

    public ActiveGreeting() {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ActiveGreeting.name = name;
    }
}
