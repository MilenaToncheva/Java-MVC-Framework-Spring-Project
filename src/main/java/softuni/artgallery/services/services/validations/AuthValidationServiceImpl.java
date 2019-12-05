package softuni.artgallery.services.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.constants.userMessages.UserErrorMessages;
import softuni.artgallery.constants.userMessages.UserRegistrationViolationMessages;
import softuni.artgallery.error.UserIllegalArgumentsException;
import softuni.artgallery.error.UserRegistrationException;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.services.validations.AuthValidationService;
import softuni.artgallery.services.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                isUsernameFree(userModel.getUsername()) && isEmailFree(userModel.getEmail())
                && isUsernameValid(userModel.getUsername()) && isEmailValid(userModel.getEmail())
                && isPasswordValid(userModel.getPassword()) && isFirstNameValid(userModel.getFirstName())
                && isLastNameValid(userModel.getLastName());
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

        if (this.userService.isEmailUnique(email) != null) {
            throw new UserRegistrationException(UserErrorMessages.USER_EMAIL_ALREADY_EXISTS);
        }
        return true;
    }

    private boolean isUsernameValid(String username) {
        if(username==null||username.equals("")||username.length()<4||username.length()>30){
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INCORRECT_USERNAME);

        }
        return true;
    }

    private boolean isFirstNameValid(String firstName) {
        if(firstName==null||firstName.equals("")||firstName.length()<3||firstName.length()>15){
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INCORRECT_FIRST_NAME);
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        Pattern pattern=Pattern.compile(CommonConstants.EMAIL_PATTERN);
        Matcher matcher=pattern.matcher(email);
        if(!matcher.matches()){
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INVALID_EMAIL);
        }

        return true;
    }

    private boolean isPasswordValid(String password) {
        Pattern pattern=Pattern.compile(UserRegistrationViolationMessages.PASSWORD_PATTERN);
        Matcher matcher=pattern.matcher(password);
        if(!matcher.matches()){
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INCORRECT_PASSWORD);
        }
        return matcher.matches();

    }

    private boolean isLastNameValid(String lastName) {
        if(lastName==null||lastName.equals("")||lastName.length()<3||lastName.length()>15){
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INCORRECT_LAST_NAME);
        }
        return true;
    }
}
