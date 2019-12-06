package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST,reason="Artwork invalid name")
public class ArtworkIllegalArgumentsException extends RuntimeException {

    public ArtworkIllegalArgumentsException() {
    }

    public ArtworkIllegalArgumentsException(String message) {
        super(message);
    }
}
