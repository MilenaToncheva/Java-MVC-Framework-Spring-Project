package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Artist was not found!")
public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException() {
    }

    public ArtistNotFoundException(String message) {
        super(message);
    }
}
