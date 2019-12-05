package softuni.artgallery.services.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.userMessages.UserErrorMessages;
import softuni.artgallery.error.UserRegistrationException;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.services.AuthValidationService;
import softuni.artgallery.services.services.UserService;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {
    private final UserService userService;

    @Autowired
    public AuthValidationServiceImpl(UserService userService) {

        this.userService = userService;
    }

    @Override
    public boolean isValid(UserRegisterServiceModel userModel) {

        return arePasswordsMatching(userModel.getPassword(), userModel.getConfirmPassword()) &&
                isUsernameFree(userModel.getUsername()) && isEmailFree(userModel.getEmail());
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserRegistrationException(UserErrorMessages.USER_PASSWORDS_DO_NOT_MATCH);
        }
        return true;
    }

    private boolean isUsernameFree(String username) {
        if (this.userService.isUsernameUnique(username) != null) {
            throw new UserRegistrationException(UserErrorMessages.USER_WITH_USERNAME_ALREADY_EXISTS);
        }
        return true;
    }

    private boolean isEmailFree(String email) {

        if(this.userService.isEmailUnique(email) != null){
            throw new UserRegistrationException(UserErrorMessages.USER_EMAIL_ALREADY_EXISTS);
        }
        return true;
    }

}
