package softuni.artgallery.services.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.artgallery.error.UserNotDeletedException;
import softuni.artgallery.services.models.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel findById(String id);

    UserServiceModel findByUsername(String username);

    UserServiceModel editUserProfile(String oldPassword, UserServiceModel userServiceModel);

    void delete(String id) throws UserNotDeletedException;

    List<UserServiceModel> findAll();

    void setUserRole(String id,String role);
UserServiceModel isUsernameUnique(String username);

    UserServiceModel isEmailUnique(String email);
}
