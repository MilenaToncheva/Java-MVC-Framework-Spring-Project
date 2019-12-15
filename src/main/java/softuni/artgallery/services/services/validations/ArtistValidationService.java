package softuni.artgallery.services.services.validations;

import softuni.artgallery.services.models.ArtistCreateServiceModel;

public interface ArtistValidationService {
    boolean isValid(ArtistCreateServiceModel artistCreateServiceModel);

}
