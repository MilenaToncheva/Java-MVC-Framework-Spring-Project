package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Greeting not found!")
public class GreetingNotFoundException extends RuntimeException{
    public GreetingNotFoundException() {
    }

    public GreetingNotFoundException(String message) {
        super(message);
    }
}
