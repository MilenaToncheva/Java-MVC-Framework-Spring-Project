package softuni.artgallery.services.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.artgallery.services.models.UserLoginServiceModel;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.models.UserServiceModel;

public interface AuthService extends UserDetailsService {
    UserServiceModel register(UserRegisterServiceModel userRegisterServiceModel);


}
