package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="User incorrect username")
public class UserIllegalArgumentsException extends RuntimeException {
    public UserIllegalArgumentsException() {
    }

    public UserIllegalArgumentsException(String message) {
        super(message);
    }
}
