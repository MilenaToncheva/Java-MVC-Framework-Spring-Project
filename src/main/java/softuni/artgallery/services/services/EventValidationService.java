package softuni.artgallery.services.services;

import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.models.EventServiceModel;

public interface EventValidationService {
    boolean isValid(EventCreateServiceModel eventCreateServiceModel);
}
