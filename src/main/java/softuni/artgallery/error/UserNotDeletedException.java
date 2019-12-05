package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason = "User cannot be deleted as has orders")
public class UserNotDeletedException extends Throwable {
    public UserNotDeletedException() {
    }

    public UserNotDeletedException(String message) {
        super(message);
    }
}
