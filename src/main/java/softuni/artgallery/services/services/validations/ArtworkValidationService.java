package softuni.artgallery.services.services.validations;

import softuni.artgallery.services.models.ArtworkCreateServiceModel;

public interface ArtworkValidationService {
    boolean isValid(ArtworkCreateServiceModel artworkCreateServiceModel);
}
