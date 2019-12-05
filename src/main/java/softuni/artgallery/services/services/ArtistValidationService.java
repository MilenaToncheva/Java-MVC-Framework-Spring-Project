package softuni.artgallery.services.services;

import softuni.artgallery.services.models.ArtistCreateServiceModel;

public interface ArtistValidationService {
    boolean isValid(ArtistCreateServiceModel artistCreateServiceModel);
}
