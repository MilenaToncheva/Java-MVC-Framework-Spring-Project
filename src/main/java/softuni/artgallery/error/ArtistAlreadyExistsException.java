package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Artist already exist!")
public class ArtistAlreadyExistsException extends RuntimeException {
    public ArtistAlreadyExistsException() {
    }

    public ArtistAlreadyExistsException(String message) {
        super(message);
    }
}
