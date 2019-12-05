package softuni.artgallery.services.services;


import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.models.EventServiceModel;

import java.util.List;

public interface EventService {
    void register(EventCreateServiceModel EventCreateServiceModel);

    void edit(String id,EventServiceModel eventServiceModel);

    void delete(String id);

    EventServiceModel findByName(String name);
    EventServiceModel findById(String id);

    List<EventServiceModel> findAll();
}
