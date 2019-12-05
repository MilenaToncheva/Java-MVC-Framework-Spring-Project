package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(code=HttpStatus.BAD_REQUEST,reason="Event incorrect data input")
public class EventIllegalArgumentException extends RuntimeException{
    public EventIllegalArgumentException() {
    }

    public EventIllegalArgumentException(String message) {
        super(message);
    }
}
