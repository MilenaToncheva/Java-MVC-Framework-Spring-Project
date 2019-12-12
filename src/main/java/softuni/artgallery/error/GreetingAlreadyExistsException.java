package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Greeting already exists in database!")
public class GreetingAlreadyExistsException extends RuntimeException {
    public GreetingAlreadyExistsException() {
    }

    public GreetingAlreadyExistsException(String message) {
        super(message);
    }
}
