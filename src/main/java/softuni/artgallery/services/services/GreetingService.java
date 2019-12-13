package softuni.artgallery.services.services;

import softuni.artgallery.services.models.GreetingCreateServiceModel;
import softuni.artgallery.services.models.GreetingServiceModel;

public interface GreetingService {

    void reduceDuration(String name);

    GreetingServiceModel findByName(String name);

    void createGreeting(GreetingServiceModel greetingCreateServiceModel);
    boolean isUnique(String name);
}
