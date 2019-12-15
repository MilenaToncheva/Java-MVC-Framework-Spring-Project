package softuni.artgallery.services.services;

import softuni.artgallery.services.models.GreetingCreateServiceModel;
import softuni.artgallery.services.models.GreetingServiceModel;
import softuni.artgallery.web.models.greeting.GreetingEditModel;

import java.util.List;

public interface GreetingService {

    void reduceDuration(String name);

    GreetingServiceModel findByName(String name);

    void createGreeting(GreetingServiceModel greetingCreateServiceModel);
    boolean isUnique(String name);

    void edit(String id, GreetingEditModel greetingEditModel);

    List<GreetingServiceModel> findAll();

    void enableGreeting(String id);

    void disableGreeting(String id);
    void deleteGreeting(String id);

    GreetingServiceModel findById(String id);
}
