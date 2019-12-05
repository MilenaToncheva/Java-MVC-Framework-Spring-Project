package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import softuni.artgallery.constants.userMessages.UserLoginViolationMessages;

@ResponseStatus(code=HttpStatus.NOT_FOUND,reason = UserLoginViolationMessages.BAD_CREDENTIALS)
public class UserLoginException extends RuntimeException{

    public UserLoginException() {
    }

    public UserLoginException(String message) {
        super(message);
    }
}
