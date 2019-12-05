package softuni.artgallery.services.services;

import softuni.artgallery.services.models.UserRegisterServiceModel;

public interface AuthValidationService {
    boolean isValid(UserRegisterServiceModel userRegisterServiceModel);
}
