package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import softuni.artgallery.constants.artworksMessages.ArtworkErrorMessages;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = ArtworkErrorMessages.ARTWORK_ALREADY_ADDED_TO_CART)
public class ArtworkAlreadyAddedInCartException extends RuntimeException {
    public ArtworkAlreadyAddedInCartException() {
    }

    public ArtworkAlreadyAddedInCartException(String message) {
        super(message);
    }
}
