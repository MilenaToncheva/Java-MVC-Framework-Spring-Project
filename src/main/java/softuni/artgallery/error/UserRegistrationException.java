package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import softuni.artgallery.constants.common.CommonConstants;

@ResponseStatus(code=HttpStatus.CONFLICT,reason = CommonConstants.INVALID_INPUT)
public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException() {
    }

    public UserRegistrationException(String message) {
        super(message);
    }
}
