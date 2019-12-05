package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason = "Artist invalid data input")
public class ArtistIllegalArgumentsException extends RuntimeException {
    public ArtistIllegalArgumentsException() {
    }

    public ArtistIllegalArgumentsException(String message) {
        super(message);
    }
}
