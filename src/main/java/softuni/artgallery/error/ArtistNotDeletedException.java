package softuni.artgallery.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.CONFLICT,reason = "Artist not deleted")
public class ArtistNotDeletedException extends RuntimeException {
    public ArtistNotDeletedException() {
    }

    public ArtistNotDeletedException(String message) {
        super(message);
    }
}
